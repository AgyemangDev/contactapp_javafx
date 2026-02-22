package fr.isen.java2.db;

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
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS person (
                    idperson INTEGER PRIMARY KEY AUTOINCREMENT,
                    lastname VARCHAR(45) NOT NULL,
                    firstname VARCHAR(45) NOT NULL,
                    nickname VARCHAR(45) NOT NULL,
                    phone_number VARCHAR(15),
                    address VARCHAR(200),
                    email_address VARCHAR(150),
                    birth_date DATE
                );
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
