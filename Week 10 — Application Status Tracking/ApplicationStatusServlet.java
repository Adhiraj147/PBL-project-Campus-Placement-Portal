package com.placement.controller;

import com.placement.dao.ApplicationStatusDAO;
import com.placement.model.ApplicationStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import com.placement.util.DatabaseConnection;

@WebServlet("/student/application-status")
public class ApplicationStatusServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("studentId") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        int studentId =
                (Integer) session.getAttribute("studentId");

        String applicationIdParameter =
                request.getParameter("applicationId");

        if (applicationIdParameter == null ||
                applicationIdParameter.trim().isEmpty()) {

            request.setAttribute(
                    "errorMessage",
                    "Application ID is required.");

            request.getRequestDispatcher(
                    "/jsp/student/application-status.jsp")
                    .forward(request, response);

            return;
        }

        try {

            int applicationId =
                    Integer.parseInt(applicationIdParameter);

            Connection connection =
                    DatabaseConnection.getConnection();

            ApplicationStatusDAO dao =
                    new ApplicationStatusDAO(connection);

            ApplicationStatus applicationStatus =
                    dao.getApplicationStatus(
                            applicationId,
                            studentId);

            if (applicationStatus == null) {

                request.setAttribute(
                        "errorMessage",
                        "Application not found.");

            } else {

                request.setAttribute(
                        "applicationStatus",
                        applicationStatus);
            }

            request.getRequestDispatcher(
                    "/jsp/student/application-status.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "errorMessage",
                    "Invalid application ID.");

            request.getRequestDispatcher(
                    "/jsp/student/application-status.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    "Unable to load application status.");

            request.getRequestDispatcher(
                    "/jsp/student/application-status.jsp")
                    .forward(request, response);
        }
    }
}
