package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MachineDAO {
	private Connection connection;

	public MachineDAO(Connection connection) {
		this.connection = connection;
	}

	public static void insertMachine(String name, double value, double usefulLife, double residualValue,
			double laserValue, double laserUsefulLife) {
		String sql = """
				    INSERT INTO Machines(name, value, usefulLife, residualValue, laserValue, laserUsefulLife)
				    VALUES (?, ?, ?, ?, ?, ?);
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, value);
			pstmt.setDouble(3, usefulLife);
			pstmt.setDouble(4, residualValue);
			pstmt.setDouble(5, laserValue);
			pstmt.setDouble(6, laserUsefulLife);
			pstmt.executeUpdate();
			System.out.println("Máquina inserida com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir máquina: " + e.getMessage());
		}
	}

	public static void removeMachine(String name) {
		String sql = """
				    DELETE FROM Machines
				    WHERE name = ?;
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Máquina removida com sucesso!");
			} else {
				System.out.println("Nenhuma máquina encontrada com esse nome.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover máquina: " + e.getMessage());
		}
	}

	public static Machine findMachine(String name) {
		String sql = "SELECT * FROM Machines;";

		try (Connection conn = DataBaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				if (name.equals(rs.getString("name"))) {
					String nameMachine = rs.getString("name");
					double value = rs.getInt("value");
					double usefulLife = rs.getInt("usefulLife");
					double residualValue = rs.getInt("residualValue");
					double laserValue = rs.getInt("laserValue");
					double laserUsefulLife = rs.getInt("laserUsefulLife");

					Machine machine = new Machine(nameMachine, value, usefulLife, residualValue, laserValue,
							laserUsefulLife);

					return machine;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar máquinas: " + e.getMessage());
		}
		return null;
	}

	public List<Machine> getAllMachines() {
		List<Machine> machines = new ArrayList<>();
		String sql = "SELECT * FROM machines";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double value = rs.getDouble("value");
				double usefulLife = rs.getDouble("usefulLife");
				double residualValue = rs.getDouble("residualValue");
				double laserValue = rs.getDouble("laserValue");
				double laserUsefulLife = rs.getDouble("laserUsefulLife");

				Machine machine = new Machine(id, name, value, usefulLife, residualValue, laserValue, laserUsefulLife);
				machine.toString();
				machines.add(machine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return machines;
	}
}