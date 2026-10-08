package com.placement.dao;

import com.placement.model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ApplicationDAO {

    private Connection connection;

    public ApplicationDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean hasAlreadyApplied(int studentId, int jobId) {

        String sql = "SELECT application_id FROM applications " +
                     "WHERE student_id = ? AND job_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, jobId);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean applyForJob(Application application) {

        String sql = "INSERT INTO applications " +
                     "(student_id, job_id, application_date, status) " +
                     "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, application.getStudentId());
            statement.setInt(2, application.getJobId());
            statement.setString(3, application.getStatus());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Application getApplication(int studentId, int jobId) {

        Application application = null;

        String sql = "SELECT application_id, student_id, job_id, " +
                     "application_date, status " +
                     "FROM applications " +
                     "WHERE student_id = ? AND job_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, jobId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                application = new Application();

                application.setApplicationId(
                        resultSet.getInt("application_id"));

                application.setStudentId(
                        resultSet.getInt("student_id"));

                application.setJobId(
                        resultSet.getInt("job_id"));

                application.setApplicationDate(
                        resultSet.getString("application_date"));

                application.setStatus(
                        resultSet.getString("status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return application;
    }
}
