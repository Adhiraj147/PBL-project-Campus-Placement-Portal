package com.placement.controller;

import com.placement.dao.ApplicationHistoryDAO;
import com.placement.model.ApplicationHistory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/student/application-history")
public class ApplicationHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ApplicationHistoryDAO applicationHistoryDAO;

    @Override
    public void init() throws ServletException {

        applicationHistoryDAO =
                new ApplicationHistoryDAO(
                        DatabaseConnection.getConnection()
                );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession();

        Integer studentId =
                (Integer) session.getAttribute("studentId");

        if (studentId == null) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/login"
            );

            return;
        }

        List<ApplicationHistory> applications =
                applicationHistoryDAO
                        .getApplicationHistory(studentId);

        request.setAttribute(
                "applications",
                applications
        );

        request.getRequestDispatcher(
                "/jsp/student/application-history.jsp"
        ).forward(request, response);
    }
}
