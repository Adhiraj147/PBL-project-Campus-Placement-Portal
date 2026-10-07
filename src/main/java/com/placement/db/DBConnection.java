package com.placement.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Enterprise Dual-Engine JDBC Connection Manager
 * Automatically detects whether external MySQL 8.0 is available;
 * smoothly falls back to the in-memory persistence layer if no MySQL is present.
 * Assigned to: Himanshu (feature/database-integration)
 */
public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static String username = "root";
    private static String password = "password";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static boolean mysqlAvailable = false;
    private static boolean tested = false;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                url = props.getProperty("db.url", url);
                username = props.getProperty("db.user", username);
                password = props.getProperty("db.password", password);
                driver = props.getProperty("db.driver", driver);
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Checks if MySQL database connection is live
     */
    public static synchronized boolean isMySQLAvailable() {
        if (tested) {
            return mysqlAvailable;
        }
        tested = true;
        try {
            Class.forName(driver);
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                mysqlAvailable = (conn != null && !conn.isClosed());
            }
        } catch (Throwable t) {
            mysqlAvailable = false;
        }
        return mysqlAvailable;
    }

    /**
     * Obtains real JDBC Connection if MySQL is present, or null for in-memory mode.
     */
    public static Connection getConnection() throws SQLException {
        if (isMySQLAvailable()) {
            return DriverManager.getConnection(url, username, password);
        }
        return null;
    }

    public static InMemoryDB getInMemoryStore() {
        return InMemoryDB.getInstance();
    }
}
