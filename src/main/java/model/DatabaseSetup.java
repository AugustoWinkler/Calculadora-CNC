package model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

	private static final String DB_FOLDER = "db";

	public static void createTables() {
		ensureDatabaseFolder();

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

		try (Connection conn = DataBaseConnection.connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(createMachinesTable);
			stmt.execute(createMaterialTable);
			stmt.execute(createOperacionalTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ensureDatabaseFolder() {
		try {
			String currentDirectory = System.getProperty("user.dir");
			Path dbFolderPath = Paths.get(currentDirectory, DB_FOLDER);

			if (!Files.exists(dbFolderPath)) {
				Files.createDirectory(dbFolderPath);
				System.out.println("Pasta 'db' criada com sucesso.");
			}
		} catch (Exception e) {
			System.err.println("Erro ao criar a pasta 'db': " + e.getMessage());
		}
	}
}
