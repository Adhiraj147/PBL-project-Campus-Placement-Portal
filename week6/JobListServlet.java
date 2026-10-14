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
import java.util.List;

@WebServlet("/student/jobs")
public class JobListServlet extends HttpServlet {

    private JobDAO jobDAO;

    @Override
    public void init() {
        jobDAO = new JobDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<Job> jobs = jobDAO.getAllActiveJobs();

            request.setAttribute("jobs", jobs);

            request.getRequestDispatcher(
                "/student/jobs.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                "error",
                "Unable to load available jobs."
            );

            request.getRequestDispatcher(
                "/student/jobs.jsp"
            ).forward(request, response);
        }
    }
}