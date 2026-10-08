package com.placement.controller;

import com.placement.dao.PortalNotificationDAO;
import com.placement.model.PortalNotification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.placement.util.DatabaseConnection;

@WebServlet("/student/notifications")
public class PortalNotificationServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("studentId") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");

            return;
        }

        int studentId =
                (Integer) session.getAttribute("studentId");

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PortalNotificationDAO dao =
                    new PortalNotificationDAO(connection);

            List<PortalNotification> notifications =
                    dao.getNotificationsByStudentId(studentId);

            request.setAttribute(
                    "notifications",
                    notifications);

            request.getRequestDispatcher(
                    "/jsp/student/notifications.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    "Unable to load notifications.");

            request.getRequestDispatcher(
                    "/jsp/student/notifications.jsp")
                    .forward(request, response);
        }
    }
}
