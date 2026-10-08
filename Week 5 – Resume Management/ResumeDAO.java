package com.placement.dao;

import com.placement.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResumeDAO {

    private Connection connection;

    public ResumeDAO(Connection connection) {
        this.connection = connection;
    }

    public Resume getResumeByStudentId(int studentId) {

        Resume resume = null;

        String sql = "SELECT resume_id, student_id, file_name, " +
                     "file_path, uploaded_date " +
                     "FROM resumes WHERE student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                resume = new Resume();

                resume.setResumeId(
                        resultSet.getInt("resume_id"));

                resume.setStudentId(
                        resultSet.getInt("student_id"));

                resume.setFileName(
                        resultSet.getString("file_name"));

                resume.setFilePath(
                        resultSet.getString("file_path"));

                resume.setUploadedDate(
                        resultSet.getString("uploaded_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resume;
    }

    public boolean saveResume(Resume resume) {

        String sql = "INSERT INTO resumes " +
                     "(student_id, file_name, file_path, uploaded_date) " +
                     "VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, resume.getStudentId());
            statement.setString(2, resume.getFileName());
            statement.setString(3, resume.getFilePath());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateResume(Resume resume) {

        String sql = "UPDATE resumes " +
                     "SET file_name = ?, file_path = ?, " +
                     "uploaded_date = CURRENT_TIMESTAMP " +
                     "WHERE student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, resume.getFileName());
            statement.setString(2, resume.getFilePath());
            statement.setInt(3, resume.getStudentId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
