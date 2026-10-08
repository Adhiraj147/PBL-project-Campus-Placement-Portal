package com.placement.controller;

import com.placement.dao.EligibilityDAO;
import com.placement.model.Eligibility;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/student/eligibility")
public class EligibilityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private EligibilityDAO eligibilityDAO;

    @Override
    public void init() throws ServletException {

        eligibilityDAO =
                new EligibilityDAO(
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

        String jobIdParameter =
                request.getParameter("jobId");

        if (jobIdParameter == null ||
            jobIdParameter.trim().isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/jobs"
            );

            return;
        }

        try {

            int jobId =
                    Integer.parseInt(jobIdParameter);

            Eligibility eligibility =
                    eligibilityDAO.checkEligibility(
                            studentId,
                            jobId
                    );

            request.setAttribute(
                    "eligibility",
                    eligibility
            );

            request.getRequestDispatcher(
                    "/jsp/student/eligibility.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/jobs"
            );
        }
    }
}
