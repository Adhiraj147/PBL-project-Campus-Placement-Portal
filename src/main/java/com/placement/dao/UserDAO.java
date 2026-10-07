package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.User;

import java.sql.*;
import java.util.List;

/**
 * User Data Access Object (DAO)
 * Assigned to: Himanshu & Adhiraj (feature/database-integration, feature/authentication)
 */
public class UserDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public User findByEmail(String email) {
        if (DBConnection.isMySQLAvailable()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return mapRow(rs);
                }
            } catch (SQLException e) {
                System.err.println("[UserDAO] MySQL error: " + e.getMessage());
            }
        }
        return inMemory.findUserByEmail(email);
    }

    public User findById(int id) {
        if (DBConnection.isMySQLAvailable()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return mapRow(rs);
                }
            } catch (SQLException e) {
                System.err.println("[UserDAO] MySQL error: " + e.getMessage());
            }
        }
        return inMemory.findUserById(id);
    }

    public User createUser(User user) {
        if (DBConnection.isMySQLAvailable()) {
            String sql = "INSERT INTO users (email, password_hash, role, full_name, phone, status) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPasswordHash());
                ps.setString(3, user.getRole().name());
                ps.setString(4, user.getFullName());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getStatus() != null ? user.getStatus().name() : "ACTIVE");
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) user.setId(rs.getInt(1));
                }
                return user;
            } catch (SQLException e) {
                System.err.println("[UserDAO] MySQL error: " + e.getMessage());
            }
        }
        return inMemory.createUser(user);
    }

    public void updateUser(User user) {
        if (DBConnection.isMySQLAvailable()) {
            String sql = "UPDATE users SET full_name = ?, phone = ?, status = ? WHERE id = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, user.getFullName());
                ps.setString(2, user.getPhone());
                ps.setString(3, user.getStatus().name());
                ps.setInt(4, user.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("[UserDAO] MySQL error: " + e.getMessage());
            }
        }
        inMemory.updateUser(user);
    }

    public List<User> getAllUsers() {
        return inMemory.getAllUsers();
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setRole(User.Role.valueOf(rs.getString("role")));
        u.setFullName(rs.getString("full_name"));
        u.setPhone(rs.getString("phone"));
        u.setStatus(User.Status.valueOf(rs.getString("status")));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        return u;
    }
}
