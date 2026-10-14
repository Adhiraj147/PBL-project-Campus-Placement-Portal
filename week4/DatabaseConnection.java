package com.placement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection — High Performance JDBC Connection Manager
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX / himanshu.yadav060107@gmail.com)
 *
 * Provides thread-safe, environment-aware JDBC connection factory for the
 * Campus Placement and Internship Portal. Supports local development, Docker
 * containers, and enterprise cloud deployments via externalized configuration.
 */
public class DatabaseConnection {

    // Database configuration — Externalized via environment variables with resilient local defaults
    private static final String URL = System.getenv("DB_URL") != null 
        ? System.getenv("DB_URL") 
        : "jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8";

    private static final String USER = System.getenv("DB_USER") != null 
        ? System.getenv("DB_USER") 
        : "root";

    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null 
        ? System.getenv("DB_PASSWORD") 
        : "password123";

    // Static initializer: Loads the MySQL 8.0 CJ JDBC driver exactly once per JVM lifecycle
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("CRITICAL: Failed to locate MySQL Connector/J driver on classpath.");
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver initialization failed.", e);
        }
    }

    /**
     * Private constructor to prevent instantiation of utility factory class.
     */
    private DatabaseConnection() {}

    /**
     * Establishes and returns an active connection to the database.
     *
     * @return Connection object configured for transactional operations
     * @throws SQLException if a database connectivity or authentication error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR: Could not establish database connection. Target: " + URL.split("\\?")[0]);
            throw e;
        }
    }
}
