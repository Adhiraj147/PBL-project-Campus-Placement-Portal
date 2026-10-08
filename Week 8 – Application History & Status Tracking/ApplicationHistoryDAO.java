package com.placement.dao;

import com.placement.model.ApplicationHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApplicationHistoryDAO {

    private Connection connection;

    public ApplicationHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ApplicationHistory> getApplicationHistory(int studentId) {

        List<ApplicationHistory> applications =
                new ArrayList<>();

        String sql =
                "SELECT a.application_id, " +
                "a.job_id, " +
                "j.title, " +
                "j.company, " +
                "j.job_type, " +
                "a.application_date, " +
                "a.status " +
                "FROM applications a " +
                "JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.student_id = ? " +
                "ORDER BY a.application_date DESC";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                ApplicationHistory application =
                        new ApplicationHistory();

                application.setApplicationId(
                        resultSet.getInt("application_id"));

                application.setJobId(
                        resultSet.getInt("job_id"));

                application.setJobTitle(
                        resultSet.getString("title"));

                application.setCompany(
                        resultSet.getString("company"));

                application.setJobType(
                        resultSet.getString("job_type"));

                application.setApplicationDate(
                        resultSet.getString("application_date"));

                application.setStatus(
                        resultSet.getString("status"));

                applications.add(application);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }
}
