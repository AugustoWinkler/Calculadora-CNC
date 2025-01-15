package model;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        String createMachinesTable = """
            CREATE TABLE IF NOT EXISTS Machines (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                value REAL,
                usefulLife REAL,
                residualValue REAL,
                laserValue REAL,
                laserUsefulLife REAL
            );
        """;
        String createMaterialTable = """
                CREATE TABLE IF NOT EXISTS Materials (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL UNIQUE,
                    value REAL
                );
            """;
        
        String createOperacionalTable = """
        		CREATE TABLE IF NOT EXISTS Operacionals (
        			id INTEGER PRIMARY KEY AUTOINCREMENT,
        			name TEXT NOT NULL UNIQUE,
        			days REAL,
        			hours REAL,
        			addCosts REAL,
        			operator REAL		
        		 );
        		""";

        try (Connection conn = DataBaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createMachinesTable);
            stmt.execute(createMaterialTable);
            stmt.execute(createOperacionalTable);
            System.out.println("Tabela Machines, Materials e Operacionals criada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
