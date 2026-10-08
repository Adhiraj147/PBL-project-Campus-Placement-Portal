package com.placement.controller;

import com.placement.dao.ApplicationDAO;
import com.placement.dao.JobDAO;
import com.placement.model.Application;
import com.placement.model.Job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/student/apply-job")
public class ApplyJobServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ApplicationDAO applicationDAO;
    private JobDAO jobDAO;

    @Override
    public void init() throws ServletException {

        applicationDAO = new ApplicationDAO(
                DatabaseConnection.getConnection()
        );

        jobDAO = new JobDAO(
                DatabaseConnection.getConnection()
        );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

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

            int jobId = Integer.parseInt(jobIdParameter);

            Job job = jobDAO.getJobById(jobId);

            if (job == null) {

                response.sendRedirect(
                        request.getContextPath() +
                        "/student/jobs"
                );

                return;
            }

            request.setAttribute("job", job);

            request.getRequestDispatcher(
                    "/jsp/student/apply-job.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/jobs"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

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

            int jobId = Integer.parseInt(jobIdParameter);

            Job job = jobDAO.getJobById(jobId);

            if (job == null) {

                response.sendRedirect(
                        request.getContextPath() +
                        "/student/jobs"
                );

                return;
            }

            boolean alreadyApplied =
                    applicationDAO.hasAlreadyApplied(
                            studentId,
                            jobId
                    );

            if (alreadyApplied) {

                request.setAttribute(
                        "error",
                        "You have already applied for this opportunity."
                );

                request.setAttribute("job", job);

                request.getRequestDispatcher(
                        "/jsp/student/apply-job.jsp"
                ).forward(request, response);

                return;
            }

            Application application =
                    new Application();

            application.setStudentId(studentId);
            application.setJobId(jobId);
            application.setStatus("PENDING");

            boolean success =
                    applicationDAO.applyForJob(application);

            if (success) {

                response.sendRedirect(
                        request.getContextPath() +
                        "/student/jobs?applied=true"
                );

            } else {

                request.setAttribute(
                        "error",
                        "Unable to submit application."
                );

                request.setAttribute("job", job);

                request.getRequestDispatcher(
                        "/jsp/student/apply-job.jsp"
                ).forward(request, response);
            }

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/jobs"
            );
        }
    }
}
