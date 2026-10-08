package com.placement.controller;

import com.placement.dao.ResumeDAO;
import com.placement.model.Resume;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet("/student/resume")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class ResumeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ResumeDAO resumeDAO;

    @Override
    public void init() throws ServletException {

        resumeDAO = new ResumeDAO(
                DatabaseConnection.getConnection()
        );
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Integer studentId =
                (Integer) session.getAttribute("studentId");

        if (studentId == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        Resume resume =
                resumeDAO.getResumeByStudentId(studentId);

        request.setAttribute("resume", resume);

        request.getRequestDispatcher(
                "/jsp/student/resume.jsp"
        ).forward(request, response);
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
                    request.getContextPath() + "/login"
            );
            return;
        }

        Part filePart = request.getPart("resumeFile");

        if (filePart == null ||
            filePart.getSize() == 0) {

            request.setAttribute(
                    "error",
                    "Please select a resume file."
            );

            doGet(request, response);
            return;
        }

        String fileName = filePart.getSubmittedFileName();

        String uploadPath =
                getServletContext().getRealPath("/uploads/resumes");

        java.io.File uploadDirectory =
                new java.io.File(uploadPath);

        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        String savedFileName =
                studentId + "_" + fileName;

        String filePath =
                uploadPath + java.io.File.separator + savedFileName;

        filePart.write(filePath);

        Resume resume = new Resume();

        resume.setStudentId(studentId);
        resume.setFileName(fileName);
        resume.setFilePath(savedFileName);

        Resume existingResume =
                resumeDAO.getResumeByStudentId(studentId);

        boolean success;

        if (existingResume == null) {
            success = resumeDAO.saveResume(resume);
        } else {
            success = resumeDAO.updateResume(resume);
        }

        if (success) {

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/resume"
            );

        } else {

            request.setAttribute(
                    "error",
                    "Unable to save resume."
            );

            doGet(request, response);
        }
    }
}
