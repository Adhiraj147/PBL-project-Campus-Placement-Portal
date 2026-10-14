package com.placement.dao;

import com.placement.model.Job;
import com.placement.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO {

    @Override
    public List<Job> getAllActiveJobs() throws SQLException {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE status = 'ACTIVE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Job job = new Job();

                job.setJobId(rs.getInt("job_id"));
                job.setCompanyId(rs.getInt("company_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setJobType(rs.getString("job_type"));
                job.setLocation(rs.getString("location"));
                job.setSalary(rs.getString("salary"));
                job.setEligibility(rs.getString("eligibility"));

                if (rs.getDate("deadline") != null) {
                    job.setDeadline(
                        rs.getDate("deadline").toString()
                    );
                }

                job.setStatus(rs.getString("status"));

                jobs.add(job);
            }
        }

        return jobs;
    }

    @Override
    public Job getJobById(int jobId) throws SQLException {

        String sql = "SELECT * FROM jobs WHERE job_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, jobId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Job job = new Job();

                    job.setJobId(rs.getInt("job_id"));
                    job.setCompanyId(rs.getInt("company_id"));
                    job.setTitle(rs.getString("title"));
                    job.setDescription(rs.getString("description"));
                    job.setJobType(rs.getString("job_type"));
                    job.setLocation(rs.getString("location"));
                    job.setSalary(rs.getString("salary"));
                    job.setEligibility(rs.getString("eligibility"));

                    if (rs.getDate("deadline") != null) {
                        job.setDeadline(
                            rs.getDate("deadline").toString()
                        );
                    }

                    job.setStatus(rs.getString("status"));

                    return job;
                }
            }
        }

        return null;
    }
}