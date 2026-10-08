package com.placement.dao;

import com.placement.model.Student;
import com.placement.model.Education;
import com.placement.model.Project;
import java.sql.SQLException;

/**
 * ============================================================================
 * CAMPUS PLACEMENT AND INTERNSHIP PORTAL
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
 * ============================================================================
 * Data Access Object Interface for Candidate Profiles and Qualifications.
 */
public interface StudentDAO {
    Student getStudentByUserId(int userId) throws SQLException;
    boolean updateStudentBasicProfile(Student student) throws SQLException;
    
    boolean addEducation(Education edu) throws SQLException;
    boolean deleteEducation(int eduId) throws SQLException;
    
    boolean addProject(Project proj) throws SQLException;
    boolean deleteProject(int projectId) throws SQLException;
    
    boolean addSkill(int studentId, String skillName) throws SQLException;
    boolean removeSkill(int studentId, String skillName) throws SQLException;
}
