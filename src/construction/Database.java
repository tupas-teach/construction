package construction;

import java.sql.*;

public class Database {

    private static final String DATABASE_URL = "jdbc:sqlite:construction.db";

    // Method to establish database connection
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Method to create tables if they don't exist
    public static void createTables(Connection conn) {
        String createProjectsTable = "CREATE TABLE IF NOT EXISTS Projects (" +
                                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                     "name TEXT NOT NULL, " +
                                     "location TEXT NOT NULL, " +
                                     "budget REAL NOT NULL)";
        String createContractorsTable = "CREATE TABLE IF NOT EXISTS Contractors (" +
                                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "name TEXT NOT NULL)";
        String createProjectContractorsTable = "CREATE TABLE IF NOT EXISTS ProjectContractors (" +
                                               "project_id INTEGER, " +
                                               "contractor_id INTEGER, " +
                                               "PRIMARY KEY (project_id, contractor_id), " +
                                               "FOREIGN KEY (project_id) REFERENCES Projects(id), " +
                                               "FOREIGN KEY (contractor_id) REFERENCES Contractors(id))";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createProjectsTable);
            stmt.executeUpdate(createContractorsTable);
            stmt.executeUpdate(createProjectContractorsTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }
}
