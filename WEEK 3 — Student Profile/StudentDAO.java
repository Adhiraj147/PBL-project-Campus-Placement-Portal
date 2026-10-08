package com.placement.dao;

import com.placement.model.Education;
import com.placement.model.Project;
import com.placement.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    /*
     * Get student profile using user ID
     */
    Student getStudentByUserId(int userId)
            throws SQLException;

    /*
     * Update basic student profile
     */
    boolean updateStudentBasicProfile(Student student)
            throws SQLException;

    /*
     * Education operations
     */
    boolean addEducation(Education education)
            throws SQLException;

    boolean deleteEducation(int educationId)
            throws SQLException;

    /*
     * Project operations
     */
    boolean addProject(Project project)
            throws SQLException;

    boolean deleteProject(int projectId)
            throws SQLException;

    /*
     * Skill operations
     */
    boolean addSkill(int studentId,
                     String skillName)
            throws SQLException;

    boolean removeSkill(int studentId,
                        String skillName)
            throws SQLException;
}
