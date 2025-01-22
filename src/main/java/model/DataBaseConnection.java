package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Paths;

public class DataBaseConnection {

	private static final String DB_NAME = "meubanco.db";
	private static final String DB_FOLDER = "db";

	public static Connection connect() {
		Connection conn = null;
		try {

			String currentDirectory = System.getProperty("user.dir");

			String dbPath = Paths.get(currentDirectory, DB_FOLDER, DB_NAME).toString();

			String url = "jdbc:sqlite:" + dbPath;
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o SQLite: " + e.getMessage());
		}
		return conn;
	}
}
