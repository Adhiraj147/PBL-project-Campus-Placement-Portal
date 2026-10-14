package com.placement.dao;

import com.placement.model.Application;
import com.placement.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDAOImpl implements ApplicationDAO {

    @Override
    public boolean applyForJob(int studentId, int jobId)
            throws SQLException {

        String sql =
            "INSERT INTO applications (student_id, job_id, status) " +
            "SELECT ?, j.job_id, 'APPLIED' " +
            "FROM jobs j " +
            "WHERE j.job_id = ? AND j.status = 'ACTIVE' " +
            "AND (j.deadline IS NULL OR j.deadline >= CURRENT_DATE) " +
            "AND NOT EXISTS (" +
            "SELECT 1 FROM applications a " +
            "WHERE a.student_id = ? AND a.job_id = j.job_id" +
            ")";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, jobId);
            stmt.setInt(3, studentId);

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean hasAlreadyApplied(int studentId, int jobId)
            throws SQLException {

        String sql =
            "SELECT 1 FROM applications " +
            "WHERE student_id = ? AND job_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, jobId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public List<Application> getApplicationsByStudentId(int studentId)
            throws SQLException {

        List<Application> applications = new ArrayList<>();

        String sql =
            "SELECT application_id, student_id, job_id, status, applied_at " +
            "FROM applications " +
            "WHERE student_id = ? " +
            "ORDER BY applied_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Application application = new Application();

                    application.setApplicationId(
                        rs.getInt("application_id")
                    );

                    application.setStudentId(
                        rs.getInt("student_id")
                    );

                    application.setJobId(
                        rs.getInt("job_id")
                    );

                    application.setStatus(
                        rs.getString("status")
                    );

                    application.setAppliedAt(
                        rs.getTimestamp("applied_at")
                    );

                    applications.add(application);
                }
            }
        }

        return applications;
    }
}