package com.placement.controller;

import com.placement.dao.UserDAO;
import com.placement.dao.UserDAOImpl;
import com.placement.model.User;
import com.placement.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Validation
        if(email == null || password == null || role == null) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
            // Check if user already exists
            if (userDAO.getUserByEmail(email) != null) {
                request.setAttribute("error", "Email is already registered.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
            
            // Hash password
            String hashedPassword = PasswordUtil.hashPassword(password);
            
            // Default status
            String status = "ACTIVE"; 
            if ("RECRUITER".equals(role)) {
                status = "PENDING"; // Recruiters might need admin approval
            }
            
            User user = new User(email, hashedPassword, role, status);
            int newUserId = userDAO.registerUser(user);
            
            if (newUserId > 0) {
                // Create specific profile based on role
                if ("STUDENT".equals(role)) {
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String rollNo = request.getParameter("rollNo");
                    
                    // Provide defaults since these are filled in the profile page later
                    if (firstName == null) firstName = "Student";
                    if (lastName == null) lastName = "User";
                    if (rollNo == null) rollNo = "N/A";
                    
                    userDAO.createStudentProfile(newUserId, firstName, lastName, rollNo);
                } else if ("RECRUITER".equals(role)) {
                    String companyName = request.getParameter("companyName");
                    
                    if (companyName == null) companyName = "My Company";
                    
                    userDAO.createCompanyProfile(newUserId, companyName);
                }
                
                request.setAttribute("success", "Registration successful. Please login.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}