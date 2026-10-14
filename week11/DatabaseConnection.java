package com.placement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
        // Prevent object creation.
    }

    public static Connection getConnection()
            throws SQLException {

        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (isBlank(url)
                || isBlank(username)
                || password == null) {

            throw new SQLException(
                "Database configuration is incomplete. " +
                "Check DB_URL, DB_USERNAME and DB_PASSWORD."
            );
        }

        return DriverManager.getConnection(
            url,
            username,
            password
        );
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}