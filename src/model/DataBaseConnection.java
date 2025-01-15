package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String URL = "jdbc:sqlite:C:/meuapp/database/meubanco.db";

	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL);
			System.out.println("Conexão estabelecida com o SQLite.");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o SQLite: " + e.getMessage());
		}
		return conn;
	}
	
}
