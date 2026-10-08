package com.placement.controller;

import com.placement.dao.StudentDAO;
import com.placement.dao.StudentDAOImpl;
import com.placement.model.Student;
import com.placement.model.User;
import com.placement.util.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/student/dashboard")
public class StudentDashboardServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );

            return;
        }

        User user = (User) session.getAttribute("user");

        int totalApplications = 0;
        int shortlisted = 0;
        int pendingInterviews = 0;

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            Student student =
                    studentDAO.getStudentByUserId(user.getUserId());

            if (student == null) {

                com.placement.dao.UserDAO userDAO =
                        new com.placement.dao.UserDAOImpl();

                userDAO.createStudentProfile(
                        user.getUserId(),
                        "Student",
                        "User",
                        "N/A"
                );

                student =
                        studentDAO.getStudentByUserId(user.getUserId());
            }

            int studentId = student.getStudentId();

            // Total applications
            String totalQuery =
                    "SELECT COUNT(*) FROM applications " +
                    "WHERE student_id = ?";

            try (PreparedStatement statement =
                         connection.prepareStatement(totalQuery)) {

                statement.setInt(1, studentId);

                try (ResultSet resultSet =
                             statement.executeQuery()) {

                    if (resultSet.next()) {
                        totalApplications =
                                resultSet.getInt(1);
                    }
                }
            }

            // Shortlisted applications
            String shortlistedQuery =
                    "SELECT COUNT(*) FROM applications " +
                    "WHERE student_id = ? " +
                    "AND status = 'SHORTLISTED'";

            try (PreparedStatement statement =
                         connection.prepareStatement(shortlistedQuery)) {

                statement.setInt(1, studentId);

                try (ResultSet resultSet =
                             statement.executeQuery()) {

                    if (resultSet.next()) {
                        shortlisted =
                                resultSet.getInt(1);
                    }
                }
            }

            // Pending interviews
            String interviewQuery =
                    "SELECT COUNT(*) FROM applications " +
                    "WHERE student_id = ? " +
                    "AND status = 'INTERVIEW_SCHEDULED'";

            try (PreparedStatement statement =
                         connection.prepareStatement(interviewQuery)) {

                statement.setInt(1, studentId);

                try (ResultSet resultSet =
                             statement.executeQuery()) {

                    if (resultSet.next()) {
                        pendingInterviews =
                                resultSet.getInt(1);
                    }
                }
            }

        } catch (SQLException exception) {

            exception.printStackTrace();

            request.setAttribute(
                    "dashboardError",
                    "Unable to load dashboard information."
            );
        }

        request.setAttribute(
                "totalApplications",
                totalApplications
        );

        request.setAttribute(
                "shortlisted",
                shortlisted
        );

        request.setAttribute(
                "pendingInterviews",
                pendingInterviews
        );

        request.getRequestDispatcher(
                "/jsp/student/dashboard.jsp"
        ).forward(request, response);
    }
}
