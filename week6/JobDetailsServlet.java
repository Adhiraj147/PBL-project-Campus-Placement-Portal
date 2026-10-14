package com.placement.controller;

import com.placement.dao.JobDAO;
import com.placement.dao.JobDAOImpl;
import com.placement.model.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student/job-details")
public class JobDetailsServlet extends HttpServlet {

    private JobDAO jobDAO;

    @Override
    public void init() {
        jobDAO = new JobDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String jobIdParameter = request.getParameter("jobId");

        if (jobIdParameter == null || jobIdParameter.trim().isEmpty()) {
            response.sendRedirect(
                request.getContextPath() + "/student/jobs"
            );
            return;
        }

        try {

            int jobId = Integer.parseInt(jobIdParameter);

            Job job = jobDAO.getJobById(jobId);

            if (job == null) {

                request.setAttribute(
                    "error",
                    "Job opportunity not found."
                );

                request.getRequestDispatcher(
                    "/student/jobs.jsp"
                ).forward(request, response);

                return;
            }

            request.setAttribute("job", job);

            request.getRequestDispatcher(
                "/student/job-details.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                request.getContextPath() + "/student/jobs"
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                "error",
                "Unable to load job details."
            );

            request.getRequestDispatcher(
                "/student/jobs.jsp"
            ).forward(request, response);
        }
    }
}