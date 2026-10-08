package com.placement.dao;

import com.placement.model.ApplicationStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplicationStatusDAO {

    private Connection connection;

    public ApplicationStatusDAO(Connection connection) {
        this.connection = connection;
    }

    public ApplicationStatus getApplicationStatus(
            int applicationId,
            int studentId) {

        ApplicationStatus applicationStatus = null;

        String sql =
                "SELECT " +
                "a.application_id, " +
                "a.student_id, " +
                "a.job_id, " +
                "a.application_date, " +
                "a.status, " +
                "j.title, " +
                "j.company, " +
                "j.job_type " +
                "FROM applications a " +
                "JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.application_id = ? " +
                "AND a.student_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, applicationId);
            statement.setInt(2, studentId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    applicationStatus =
                            new ApplicationStatus();

                    applicationStatus.setApplicationId(
                            resultSet.getInt("application_id"));

                    applicationStatus.setStudentId(
                            resultSet.getInt("student_id"));

                    applicationStatus.setJobId(
                            resultSet.getInt("job_id"));

                    applicationStatus.setApplicationDate(
                            String.valueOf(
                                    resultSet.getTimestamp(
                                            "application_date")));

                    applicationStatus.setStatus(
                            resultSet.getString("status"));

                    applicationStatus.setJobTitle(
                            resultSet.getString("title"));

                    applicationStatus.setCompany(
                            resultSet.getString("company"));

                    applicationStatus.setJobType(
                            resultSet.getString("job_type"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicationStatus;
    }
}
