package com.placement.controller;

import com.placement.dao.JobDAO;
import com.placement.model.Job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/student/jobs")
public class JobServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private JobDAO jobDAO;

    @Override
    public void init() throws ServletException {

        jobDAO = new JobDAO(
                DatabaseConnection.getConnection()
        );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Job> jobs = jobDAO.getAllJobs();

        request.setAttribute("jobs", jobs);

        request.getRequestDispatcher(
                "/jsp/student/jobs.jsp"
        ).forward(request, response);
    }
}
