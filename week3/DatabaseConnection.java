package com.placement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ============================================================================
 * CAMPUS PLACEMENT AND INTERNSHIP PORTAL
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
 * ============================================================================
 * Centralized JDBC Connection Factory.
 * Manages database driver lifecycle and provides thread-safe connection pooling
 * and factory access to the underlying MySQL relational instance.
 */
public class DatabaseConnection {

    // Default configuration (Supports Environment Variables or Local / Cloud Configuration)
    private static final String DEFAULT_URL =
        "jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "password123";

    // Dynamic resolution supporting environment variables (DB_URL, DB_USER, DB_PASSWORD)
    private static final String URL = System.getenv("DB_URL") != null ? 
            System.getenv("DB_URL") : DEFAULT_URL;
    private static final String USER = System.getenv("DB_USER") != null ? 
            System.getenv("DB_USER") : DEFAULT_USER;
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? 
            System.getenv("DB_PASSWORD") : DEFAULT_PASSWORD;

    // Load MySQL Connector/J driver class exactly once via static initializer
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("[DatabaseConnection] CRITICAL: MySQL JDBC Driver not found in classpath.");
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC Driver.", e);
        }
    }

    /**
     * Private constructor to prevent direct instantiation.
     */
    private DatabaseConnection() {}

    /**
     * Retrieves an active database connection from DriverManager.
     * 
     * @return Connection active JDBC connection to MySQL
     * @throws SQLException if a database access or network error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Helper method to verify database connectivity.
     * 
     * @return true if connection is established and valid; false otherwise.
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("[DatabaseConnection] Connection test failed: " + e.getMessage());
            return false;
        }
    }
}
