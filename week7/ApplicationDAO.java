package com.placement.dao;

import com.placement.model.Application;

import java.sql.SQLException;
import java.util.List;

public interface ApplicationDAO {

    boolean applyForJob(int studentId, int jobId)
            throws SQLException;

    List<Application> getApplicationsByStudentId(int studentId)
            throws SQLException;

    boolean hasAlreadyApplied(int studentId, int jobId)
            throws SQLException;
}