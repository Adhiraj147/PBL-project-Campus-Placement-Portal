package com.placement.controller;

import com.placement.dao.AdminDAO;
import com.placement.dao.AdminDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private AdminDAO adminDAO;

    @Override
    public void init() {
        adminDAO = new AdminDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("userId") == null) {

            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );

            return;
        }

        if (!"ADMIN".equals(session.getAttribute("role"))) {

            response.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                "Access denied."
            );

            return;
        }

        try {

            request.setAttribute(
                "totalUsers",
                adminDAO.getTotalUsers()
            );

            request.setAttribute(
                "totalStudents",
                adminDAO.getUsersByRole("STUDENT")
            );

            request.setAttribute(
                "totalRecruiters",
                adminDAO.getUsersByRole("RECRUITER")
            );

            request.getRequestDispatcher(
                "/admin/dashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            getServletContext().log(
                "Unable to load admin dashboard.",
                e
            );

            response.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Unable to load the admin dashboard."
            );
        }
    }
}