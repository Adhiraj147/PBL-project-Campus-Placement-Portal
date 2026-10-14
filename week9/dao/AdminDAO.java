package com.placement.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 * AdminDAO — Training & Placement Office Analytics Persistence Interface
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX / himanshu.yadav060107@gmail.com)
 */
public interface AdminDAO {
    /**
     * Aggregates real-time institution-wide placement KPIs across all operational tables.
     * @return Map of metric identifiers to aggregated counts
     * @throws SQLException on database errors
     */
    Map<String, Integer> getSystemStatistics() throws SQLException;
}
