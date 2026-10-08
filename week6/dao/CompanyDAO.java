package com.placement.dao;

import com.placement.model.Company;
import java.sql.SQLException;

/**
 * ============================================================================
 * CAMPUS PLACEMENT AND INTERNSHIP PORTAL
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
 * ============================================================================
 * Data Access Object Interface for Corporate Recruiter Entities.
 */
public interface CompanyDAO {
    Company getCompanyByUserId(int userId) throws SQLException;
    Company getCompanyById(int companyId) throws SQLException;
    boolean updateCompanyProfile(Company company) throws SQLException;
}
