package com.placement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * User Entity Model
 * Assigned to: Adhiraj (feature/authentication)
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Role {
        STUDENT, RECRUITER, ADMIN
    }

    public enum Status {
        ACTIVE, PENDING, SUSPENDED
    }

    private int id;
    private String email;
    private String passwordHash;
    private Role role;
    private String fullName;
    private String phone;
    private Status status;
    private Timestamp createdAt;

    public User() {}

    public User(int id, String email, String passwordHash, Role role, String fullName, String phone, Status status) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
