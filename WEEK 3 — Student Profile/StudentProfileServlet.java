package com.placement.controller;

import com.placement.dao.StudentDAO;
import com.placement.dao.StudentDAOImpl;
import com.placement.model.Education;
import com.placement.model.Project;
import com.placement.model.Student;
import com.placement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/student/profile")
public class StudentProfileServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );

            return;
        }

        User user = (User) session.getAttribute("user");

        try {

            Student student =
                    studentDAO.getStudentByUserId(user.getUserId());

            if (student == null) {

                com.placement.dao.UserDAO userDAO =
                        new com.placement.dao.UserDAOImpl();

                userDAO.createStudentProfile(
                        user.getUserId(),
                        "Student",
                        "User",
                        "N/A"
                );

                student =
                        studentDAO.getStudentByUserId(
                                user.getUserId()
                        );
            }

            request.setAttribute("student", student);

            request.setAttribute(
                    "completionScore",
                    student.calculateProfileCompletion()
            );

            request.getRequestDispatcher(
                    "/jsp/student/profile.jsp"
            ).forward(request, response);

        } catch (SQLException exception) {

            exception.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Database error"
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );

            return;
        }

        User user = (User) session.getAttribute("user");

        String action = request.getParameter("action");

        try {

            Student student =
                    studentDAO.getStudentByUserId(user.getUserId());

            if (student == null) {

                com.placement.dao.UserDAO userDAO =
                        new com.placement.dao.UserDAOImpl();

                userDAO.createStudentProfile(
                        user.getUserId(),
                        "Student",
                        "User",
                        "N/A"
                );

                student =
                        studentDAO.getStudentByUserId(
                                user.getUserId()
                        );
            }

            int studentId = student.getStudentId();

            /*
             * Update basic student information
             */
            if ("updateBasic".equals(action)) {

                student.setFirstName(
                        request.getParameter("firstName")
                );

                student.setLastName(
                        request.getParameter("lastName")
                );

                student.setRollNo(
                        request.getParameter("rollNo")
                );

                student.setBranch(
                        request.getParameter("branch")
                );

                student.setGraduationYear(
                        Integer.parseInt(
                                request.getParameter(
                                        "graduationYear"
                                )
                        )
                );

                student.setCgpa(
                        Double.parseDouble(
                                request.getParameter("cgpa")
                        )
                );

                student.setPhone(
                        request.getParameter("phone")
                );

                student.setResumeUrl(
                        request.getParameter("resumeUrl")
                );

                studentDAO.updateStudentBasicProfile(student);
            }

            /*
             * Add education
             */
            else if ("addEducation".equals(action)) {

                Education education = new Education();

                education.setStudentId(studentId);

                education.setDegree(
                        request.getParameter("degree")
                );

                education.setInstitution(
                        request.getParameter("institution")
                );

                education.setPassingYear(
                        Integer.parseInt(
                                request.getParameter(
                                        "passingYear"
                                )
                        )
                );

                education.setPercentage(
                        Double.parseDouble(
                                request.getParameter(
                                        "percentage"
                                )
                        )
                );

                studentDAO.addEducation(education);
            }

            /*
             * Delete education
             */
            else if ("deleteEducation".equals(action)) {

                int educationId =
                        Integer.parseInt(
                                request.getParameter("eduId")
                        );

                studentDAO.deleteEducation(educationId);
            }

            /*
             * Add project
             */
            else if ("addProject".equals(action)) {

                Project project = new Project();

                project.setStudentId(studentId);

                project.setTitle(
                        request.getParameter("title")
                );

                project.setDescription(
                        request.getParameter("description")
                );

                project.setLink(
                        request.getParameter("link")
                );

                studentDAO.addProject(project);
            }

            /*
             * Delete project
             */
            else if ("deleteProject".equals(action)) {

                int projectId =
                        Integer.parseInt(
                                request.getParameter("projectId")
                        );

                studentDAO.deleteProject(projectId);
            }

            /*
             * Add skill
             */
            else if ("addSkill".equals(action)) {

                String skillName =
                        request.getParameter("skillName");

                if (skillName != null &&
                        !skillName.trim().isEmpty()) {

                    studentDAO.addSkill(
                            studentId,
                            skillName.trim()
                    );
                }
            }

            /*
             * Remove skill
             */
            else if ("removeSkill".equals(action)) {

                String skillName =
                        request.getParameter("skillName");

                studentDAO.removeSkill(
                        studentId,
                        skillName
                );
            }

            response.sendRedirect(
                    request.getContextPath() +
                    "/student/profile"
            );

        } catch (SQLException |
                 NumberFormatException exception) {

            exception.printStackTrace();

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error updating profile"
            );
        }
    }
}
