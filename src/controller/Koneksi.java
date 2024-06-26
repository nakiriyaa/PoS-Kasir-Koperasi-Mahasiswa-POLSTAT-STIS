package controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {
    // SQLite database file path
    private static final String DB_URL = "jdbc:sqlite:src/wirus.db";
    private static final Logger logger = Logger.getLogger(Koneksi.class.getName());
    
    static Connection getConnection() throws SQLException {
        // Check if the database file exists
        File dbFile = new File("src/wirus.db");
        logger.log(Level.INFO, "Database path: " + dbFile.getAbsolutePath());
        if (!dbFile.exists()) {
            throw new SQLException("Database file not found: " + dbFile.getAbsolutePath());
        }
        return DriverManager.getConnection(DB_URL);
    }
    
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                logger.log(Level.INFO, "Connection to the database established successfully.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to connect to the database.", e);
        }
    }
}
