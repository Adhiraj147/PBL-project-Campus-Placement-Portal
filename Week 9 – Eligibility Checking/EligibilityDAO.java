package com.placement.dao;

import com.placement.model.Eligibility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EligibilityDAO {

    private Connection connection;

    public EligibilityDAO(Connection connection) {
        this.connection = connection;
    }

    public Eligibility checkEligibility(int studentId, int jobId) {

        Eligibility eligibility = new Eligibility();

        String sql =
                "SELECT s.student_id, s.cgpa, s.skills, " +
                "j.job_id, j.minimum_cgpa, j.skills " +
                "FROM students s " +
                "CROSS JOIN jobs j " +
                "WHERE s.student_id = ? AND j.job_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);
            statement.setInt(2, jobId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                double studentCgpa =
                        resultSet.getDouble("cgpa");

                double requiredCgpa =
                        resultSet.getDouble("minimum_cgpa");

                String studentSkills =
                        resultSet.getString("skills");

                String requiredSkills =
                        resultSet.getString("skills");

                eligibility.setStudentId(studentId);
                eligibility.setJobId(jobId);

                eligibility.setStudentCgpa(studentCgpa);
                eligibility.setRequiredCgpa(requiredCgpa);

                eligibility.setStudentSkills(studentSkills);
                eligibility.setRequiredSkills(requiredSkills);

                boolean cgpaEligible =
                        studentCgpa >= requiredCgpa;

                boolean skillsEligible =
                        checkSkills(
                                studentSkills,
                                requiredSkills
                        );

                eligibility.setCgpaEligible(
                        cgpaEligible
                );

                eligibility.setSkillsEligible(
                        skillsEligible
                );

                eligibility.setEligible(
                        cgpaEligible &&
                        skillsEligible
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return eligibility;
    }

    private boolean checkSkills(String studentSkills,
                                String requiredSkills) {

        if (requiredSkills == null ||
            requiredSkills.trim().isEmpty()) {

            return true;
        }

        if (studentSkills == null ||
            studentSkills.trim().isEmpty()) {

            return false;
        }

        String studentSkillText =
                studentSkills.toLowerCase();

        String[] requiredSkillList =
                requiredSkills.split(",");

        for (String skill : requiredSkillList) {

            String requiredSkill =
                    skill.trim().toLowerCase();

            if (!studentSkillText.contains(requiredSkill)) {
                return false;
            }
        }

        return true;
    }
}
