# Week 5 - Student Module Development

## Overview

During Week 5, I worked on the Student Module of the Campus Placement and Internship Portal.

The main objective was to provide authenticated students with a dedicated dashboard where they can access their student-related information after successful login.

## Objectives

The objectives completed during this week were:

- Create the student dashboard.
- Retrieve student information from the database.
- Connect the student dashboard with the logged-in user session.
- Implement Servlet-based request handling.
- Display student information using JSP.
- Handle cases where a student profile is not available.
- Test student login and dashboard navigation.

## Student Module Components

The Student Module consists of:

1. Student Model
2. Student DAO
3. Student DAO Implementation
4. Student Dashboard Servlet
5. Student Dashboard JSP

## Working Flow

The student module follows this flow:

Student Login
      ↓
LoginServlet
      ↓
Session Creation
      ↓
StudentDashboardServlet
      ↓
Retrieve Student Information
      ↓
Student DAO
      ↓
MySQL Database
      ↓
Student Dashboard JSP
      ↓
Display Student Information

## Student Dashboard

The student dashboard acts as the main interface after successful student login.

The dashboard can be used as the base for future student features such as:

- Student profile
- Job listings
- Internship listings
- Job applications
- Application status
- Resume management

## Backend Integration

The StudentDashboardServlet receives the logged-in user's information from the HTTP session.

The servlet then retrieves the corresponding student profile from the database through the DAO layer.

This keeps database operations separate from the presentation layer.

## Error Handling

During development, handling missing student profiles was considered important.

If a user account exists but a corresponding student profile is not available, the application should handle the situation instead of directly accessing a null Student object.

This prevents errors such as:

`NullPointerException`

## Technologies Used

- Java
- Java Servlets
- JSP
- JDBC
- MySQL
- Apache Tomcat
- Maven
- HTML/CSS

## Week 5 Outcome

The Student Module was successfully integrated with the authentication system.

After successful student login, the user can be redirected to the student dashboard and student information can be retrieved from the database.