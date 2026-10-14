package com.placement.dao;

import com.placement.model.User;
import java.sql.SQLException;

/**
 * UserDAO — Identity and Access Management Persistence Contract
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX / himanshu.yadav060107@gmail.com)
 */
public interface UserDAO {
    /**
     * Registers a new user account.
     * @param user The user entity to persist
     * @return generated user_id if successful, or -1 if failed
     * @throws SQLException on database errors
     */
    int registerUser(User user) throws SQLException;

    /**
     * Finds an existing user by email address.
     * @param email The target email address
     * @return User object if found, otherwise null
     * @throws SQLException on database errors
     */
    User getUserByEmail(String email) throws SQLException;
    
    /**
     * Creates an associated student profile record.
     */
    boolean createStudentProfile(int userId, String firstName, String lastName, String rollNo) throws SQLException;
    
    /**
     * Creates an associated corporate employer profile record.
     */
    boolean createCompanyProfile(int userId, String companyName) throws SQLException;

    /**
     * Atomically registers a student user and candidate profile within a single ACID transaction.
     */
    int registerStudentWithTransaction(User user, String firstName, String lastName, String rollNo) throws SQLException;

    /**
     * Atomically registers a recruiter user and company profile within a single ACID transaction.
     */
    int registerRecruiterWithTransaction(User user, String companyName) throws SQLException;
}
