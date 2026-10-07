package com.placement.auth;

import com.placement.dao.CompanyDAO;
import com.placement.dao.StudentDAO;
import com.placement.dao.UserDAO;
import com.placement.db.DBUtils;
import com.placement.model.CompanyProfile;
import com.placement.model.StudentProfile;
import com.placement.model.User;

/**
 * Authentication & Identity Service
 * Module 1: Authentication & User Management
 * Assigned to: Adhiraj (feature/authentication)
 */
public class AuthService {
    private final UserDAO userDAO = new UserDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final CompanyDAO companyDAO = new CompanyDAO();

    /**
     * Authenticate user with email and plain password
     */
    public User login(String email, String password) {
        if (email == null || password == null) return null;
        User user = userDAO.findByEmail(email.trim());
        if (user == null) return null;

        if (DBUtils.checkPassword(password, user.getPasswordHash())) {
            if (user.getStatus() == User.Status.SUSPENDED) {
                throw new IllegalStateException("Account has been suspended by TPO administrator.");
            }
            return user;
        }
        return null;
    }

    /**
     * Register a new student
     */
    public User registerStudent(String email, String password, String fullName, String phone,
                                String rollNumber, String branch, double cgpa, int gradYear) {
        if (userDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }

        User user = new User();
        user.setEmail(email.trim());
        user.setPasswordHash(DBUtils.hashPassword(password));
        user.setRole(User.Role.STUDENT);
        user.setFullName(fullName.trim());
        user.setPhone(phone != null ? phone.trim() : "");
        user.setStatus(User.Status.ACTIVE);
        User createdUser = userDAO.createUser(user);

        StudentProfile profile = new StudentProfile();
        profile.setUserId(createdUser.getId());
        profile.setRollNumber(rollNumber.trim());
        profile.setBranch(branch.trim());
        profile.setCgpa(cgpa);
        profile.setGraduationYear(gradYear);
        profile.setStudentName(fullName.trim());
        profile.setStudentEmail(email.trim());
        profile.setPhone(phone);
        studentDAO.save(profile);

        return createdUser;
    }

    /**
     * Register a new corporate recruiter
     */
    public User registerRecruiter(String email, String password, String fullName, String phone,
                                  String companyName, String industry, String location, String website) {
        if (userDAO.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }

        User user = new User();
        user.setEmail(email.trim());
        user.setPasswordHash(DBUtils.hashPassword(password));
        user.setRole(User.Role.RECRUITER);
        user.setFullName(fullName.trim());
        user.setPhone(phone != null ? phone.trim() : "");
        user.setStatus(User.Status.ACTIVE);
        User createdUser = userDAO.createUser(user);

        CompanyProfile cp = new CompanyProfile();
        cp.setUserId(createdUser.getId());
        cp.setCompanyName(companyName.trim());
        cp.setIndustry(industry != null ? industry.trim() : "Technology");
        cp.setLocation(location != null ? location.trim() : "Campus");
        cp.setWebsite(website != null ? website.trim() : "");
        cp.setRecruiterName(fullName.trim());
        cp.setRecruiterEmail(email.trim());
        cp.setRecruiterPhone(phone);
        cp.setVerificationStatus(CompanyProfile.VerificationStatus.PENDING);
        companyDAO.save(cp);

        return createdUser;
    }

    /**
     * Validates if a user role is permitted to access a given URL path
     */
    public boolean isAuthorized(User user, String path) {
        if (user == null) return false;
        if (path.startsWith("/admin") && user.getRole() != User.Role.ADMIN) return false;
        if (path.startsWith("/recruiter") && user.getRole() != User.Role.RECRUITER) return false;
        if (path.startsWith("/student") && user.getRole() != User.Role.STUDENT) return false;
        return true;
    }
}
