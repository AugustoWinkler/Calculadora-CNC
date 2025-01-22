package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
	private Connection connection;

	public MaterialDAO(Connection connection) {
		this.connection = connection;
	}

	public static void insertMaterial(String name, double value) {
		String sql = """
				    INSERT INTO Materials(name, value)
				    VALUES (?, ?);
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, value);
			pstmt.executeUpdate();
			System.out.println("Material inserido com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir material: " + e.getMessage());
		}
	}

	public static void removeMaterial(String name) {
		String sql = """
				    DELETE FROM Materials
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
	
	public static void editMaterial(String oldName, String newName, double value) {
		String sql = """
				UPDATE Materials
				SET name = ?, value = ?
				WHERE name = ?;
				""";

		try (Connection conn = DataBaseConnection.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newName);
			pstmt.setDouble(2, value);
			pstmt.setString(3, oldName);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Material updated successfully.");
			} else {
				System.out.println("No material found with the given name.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Material findMaterial(String name) {
		String sql = "SELECT * FROM Materials;";

		try (Connection conn = DataBaseConnection.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				if (name.equals(rs.getString("name"))) {
					String nameMaterial = rs.getString("name");
					double value = rs.getInt("value");

					Material material = new Material(nameMaterial, value);

					return material;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar materiais: " + e.getMessage());
		}
		return null;
	}

	public List<Material> getAllMaterials() {
		List<Material> materials = new ArrayList<>();
		String sql = "SELECT * FROM materials";

		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double value = rs.getDouble("value");

				Material material = new Material(id, name, value);
				materials.add(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return materials;
	}
}