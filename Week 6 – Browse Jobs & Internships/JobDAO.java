package com.placement.dao;

import com.placement.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    private Connection connection;

    public JobDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Job> getAllJobs() {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT job_id, title, company, location, " +
                     "job_type, description, skills, minimum_cgpa, deadline " +
                     "FROM jobs ORDER BY deadline ASC";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Job job = new Job();

                job.setJobId(
                        resultSet.getInt("job_id"));

                job.setTitle(
                        resultSet.getString("title"));

                job.setCompany(
                        resultSet.getString("company"));

                job.setLocation(
                        resultSet.getString("location"));

                job.setJobType(
                        resultSet.getString("job_type"));

                job.setDescription(
                        resultSet.getString("description"));

                job.setSkills(
                        resultSet.getString("skills"));

                job.setMinimumCgpa(
                        resultSet.getDouble("minimum_cgpa"));

                job.setDeadline(
                        resultSet.getString("deadline"));

                jobs.add(job);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jobs;
    }

    public Job getJobById(int jobId) {

        Job job = null;

        String sql = "SELECT job_id, title, company, location, " +
                     "job_type, description, skills, minimum_cgpa, deadline " +
                     "FROM jobs WHERE job_id = ?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, jobId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                job = new Job();

                job.setJobId(
                        resultSet.getInt("job_id"));

                job.setTitle(
                        resultSet.getString("title"));

                job.setCompany(
                        resultSet.getString("company"));

                job.setLocation(
                        resultSet.getString("location"));

                job.setJobType(
                        resultSet.getString("job_type"));

                job.setDescription(
                        resultSet.getString("description"));

                job.setSkills(
                        resultSet.getString("skills"));

                job.setMinimumCgpa(
                        resultSet.getDouble("minimum_cgpa"));

                job.setDeadline(
                        resultSet.getString("deadline"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return job;
    }
}
