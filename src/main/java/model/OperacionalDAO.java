package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperacionalDAO {
	private Connection connection;

	public OperacionalDAO(Connection connection) {
		this.connection = connection;
	}

	public static void insertOperacional(String name, double days, double hours, double addCosts, double operator) {
		String sql = """
				    INSERT INTO Operacionals(name, days, hours, addCosts, operator)
				    VALUES (?, ? ,? ,? ,?);
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, days);
			pstmt.setDouble(3, hours);
			pstmt.setDouble(4, addCosts);
			pstmt.setDouble(5, operator);
			pstmt.executeUpdate();
			System.out.println("Operacional inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir operacional: " + e.getMessage());
		}
	}

	public static Operacional findOperacional(String name) {
		String sql = "SELECT * FROM Operacionals;";

		try (Connection conn = DataBaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				if (name.equals(rs.getString("name"))) {
					String nameOperacional = rs.getString("name");
					double days = rs.getInt("days");
					double hours = rs.getInt("hours");
					double addCosts = rs.getInt("addCosts");
					double operator = rs.getInt("operator");

					Operacional operacional = new Operacional(nameOperacional, days, hours, addCosts, operator);

					return operacional;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar operacionais: " + e.getMessage());
		}
		return null;
	}

	public static void removeOperacional(String name) {
		String sql = """
				    DELETE FROM Operacionals
				    WHERE name = ?;
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Operacional removida com sucesso!");
			} else {
				System.out.println("Nenhum operacional encontrado com esse nome.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover operacional: " + e.getMessage());
		}
	}

	public static void editOperacional(String oldName, String newName, double days, double hours, double addCosts,
			double operator) {
		String sql = """
				UPDATE Operacionals
				SET name = ?, days = ?, hours = ?, addCosts = ?, operator = ?
				WHERE name = ?;
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newName);
			pstmt.setDouble(2, days);
			pstmt.setDouble(3, hours);
			pstmt.setDouble(4, addCosts);
			pstmt.setDouble(5, operator);
			pstmt.setString(6, oldName);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Operacional updated successfully.");
			} else {
				System.out.println("No operacional found with the given name.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Operacional> getAllOperacionals() {
		List<Operacional> operacionals = new ArrayList<>();
		String sql = "SELECT * FROM operacionals";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double days = rs.getDouble("days");
				double hours = rs.getDouble("hours");
				double addCosts = rs.getDouble("addCosts");
				double operator = rs.getDouble("operator");

				Operacional operacional = new Operacional(id, name, days, hours, addCosts, operator);
				operacionals.add(operacional);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return operacionals;
	}
}