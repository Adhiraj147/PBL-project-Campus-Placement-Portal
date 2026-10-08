package com.placement.dao;

import com.placement.model.Certification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Connection connection;

    public StudentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Certification> getCertificationsByStudentId(int studentId) {

        List<Certification> certifications = new ArrayList<>();

        String sql = "SELECT certification_id, student_id, name, " +
                     "issuing_organization, issue_date, credential_id, " +
                     "credential_url " +
                     "FROM certifications WHERE student_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Certification certification = new Certification();

                certification.setCertificationId(
                        resultSet.getInt("certification_id"));

                certification.setStudentId(
                        resultSet.getInt("student_id"));

                certification.setName(
                        resultSet.getString("name"));

                certification.setIssuingOrganization(
                        resultSet.getString("issuing_organization"));

                certification.setIssueDate(
                        resultSet.getString("issue_date"));

                certification.setCredentialId(
                        resultSet.getString("credential_id"));

                certification.setCredentialUrl(
                        resultSet.getString("credential_url"));

                certifications.add(certification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return certifications;
    }

    @Override
    public boolean addCertification(Certification certification) {

        String sql = "INSERT INTO certifications " +
                     "(student_id, name, issuing_organization, " +
                     "issue_date, credential_id, credential_url) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, certification.getStudentId());
            statement.setString(2, certification.getName());
            statement.setString(3, certification.getIssuingOrganization());
            statement.setString(4, certification.getIssueDate());
            statement.setString(5, certification.getCredentialId());
            statement.setString(6, certification.getCredentialUrl());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCertification(int certificationId) {

        String sql = "DELETE FROM certifications WHERE certification_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, certificationId);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
