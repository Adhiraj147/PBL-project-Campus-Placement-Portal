package com.placement.dao;

import com.placement.model.Job;
import java.sql.SQLException;
import java.util.List;

public interface JobDAO {

    List<Job> getAllActiveJobs() throws SQLException;

    Job getJobById(int jobId) throws SQLException;
}