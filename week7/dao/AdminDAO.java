package com.placement.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 * ============================================================================
 * CAMPUS PLACEMENT AND INTERNSHIP PORTAL
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
 * ============================================================================
 * Data Access Object Interface for Placement Office (TPO) Executive Analytics.
 */
public interface AdminDAO {
    Map<String, Integer> getSystemStatistics() throws SQLException;
}
