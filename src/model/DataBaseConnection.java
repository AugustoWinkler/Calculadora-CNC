package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Paths;

public class DataBaseConnection {

    // Caminho relativo para o banco de dados dentro da pasta "db"
    private static final String DB_NAME = "meubanco.db";
    private static final String DB_FOLDER = "db"; // Pasta onde o banco de dados está localizado

    public static Connection connect() {
        Connection conn = null;
        try {
            // Obtém o diretório onde o .jar está sendo executado
            String currentDirectory = System.getProperty("user.dir");

            // Constrói o caminho para o banco de dados relativo ao diretório atual
            String dbPath = Paths.get(currentDirectory, DB_FOLDER, DB_NAME).toString();

            // Cria a URL de conexão com o SQLite
            String url = "jdbc:sqlite:" + dbPath;
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com o SQLite.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o SQLite: " + e.getMessage());
        }
        return conn;
    }
}
