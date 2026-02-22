package fr.isen.java2.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:sqlite:person.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    public static void initDatabase() {
        System.out.println("Initializing database...");

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             InputStream is = DatabaseManager.class.getResourceAsStream("/sql/schema.sql")) {

            if (is == null) {
                throw new RuntimeException("schema.sql not found in resources");
            }

            String sql = new String(is.readAllBytes());

            String[] statements = sql.split(";");

            for (String s : statements) {
                s = s.trim();
                if (!s.isEmpty()) {
                    stmt.execute(s);
                }
            }

            System.out.println("Database initialized successfully!");

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
    
}
