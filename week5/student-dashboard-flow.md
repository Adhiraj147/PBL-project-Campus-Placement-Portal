# Student Dashboard Flow

## Purpose

The Student Dashboard provides the authenticated student with a dedicated area of the Campus Placement and Internship Portal.

## Request Flow

```text
Student
   |
   | Login
   ↓
LoginServlet
   |
   | Create HTTP Session
   ↓
StudentDashboardServlet
   |
   | Get logged-in User ID
   ↓
StudentDAO
   |
   | JDBC Query
   ↓
MySQL Database
   |
   | Student Profile
   ↓
StudentDashboardServlet
   |
   | Set Request Attribute
   ↓
dashboard.jsp
   |
   ↓
Student Dashboard



---

# 3. Actual Code Contribution

Now comes the important part you asked for earlier: **actual code files**, not only `.md` files.

### `StudentDashboardServlet.java`

Use your **existing working file**. Don't replace it blindly.

If your current implementation is similar to this, this is the type of code that represents the Week 5 contribution:

```java
package com.placement.controller;

import com.placement.dao.StudentDAO;
import com.placement.dao.StudentDAOImpl;
import com.placement.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/student/dashboard")
public class StudentDashboardServlet extends HttpServlet {

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
            session.getAttribute("userId") == null) {

            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        try {

            Student student = studentDAO.getStudentByUserId(userId);

            if (student == null) {
                request.setAttribute(
                    "error",
                    "Student profile not found."
                );

                request.getRequestDispatcher(
                    "/login.jsp"
                ).forward(request, response);

                return;
            }

            request.setAttribute("student", student);

            request.getRequestDispatcher(
                "/student/dashboard.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                "error",
                "Unable to load student dashboard."
            );

            request.getRequestDispatcher(
                "/login.jsp"
            ).forward(request, response);
        }
    }
}