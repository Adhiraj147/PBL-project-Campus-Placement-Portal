# Week 7 - Student Job Application Management

## 1. Overview

During Week 7, I focused on the Student Job Application Management
Module of the Campus Placement and Internship Portal.

The purpose of this module is to connect students with available
placement and internship opportunities.

It provides the foundation for students to submit applications
and monitor their progress through the recruitment process.

## 2. Objectives

The objectives of this module are:

- Allow students to apply for available job opportunities.
- Associate each application with the correct student.
- Associate each application with the selected job.
- Store application information in the MySQL database.
- Prevent duplicate applications for the same job.
- Display the student's submitted applications.
- Track application status.
- Integrate application functionality with the student dashboard.

## 3. Main Components

The application module consists of:

1. Application Model
2. Application DAO
3. Application DAO Implementation
4. Apply Job Servlet
5. My Applications Servlet
6. Application JSP Pages
7. MySQL Database Integration

## 4. Application Information

An application may contain:

- Application ID
- Student ID
- Job ID
- Application Status
- Application Submission Date

The exact fields depend on the existing database schema.

## 5. Application Workflow

Student Login
    |
    v
Student Dashboard
    |
    v
Available Jobs
    |
    v
Select Job
    |
    v
Apply for Job
    |
    v
Validate Application
    |
    v
Save Application in Database
    |
    v
Display Confirmation

## 6. Duplicate Application Prevention

A student should not be able to apply for the same job repeatedly.

The application layer should check for an existing application,
and the database should enforce uniqueness where appropriate.

## 7. Application Status

The application status represents the current stage of the
recruitment process.

Possible statuses include:

- APPLIED
- UNDER_REVIEW
- SHORTLISTED
- REJECTED
- SELECTED

Only use statuses supported by the actual project implementation.

## 8. Database Integration

The application module uses JDBC to communicate with MySQL.

The DAO layer handles database queries, while Servlets process
HTTP requests and JSP pages display information.

## 9. Testing

Testing should cover:

- Successful job application.
- Duplicate application attempts.
- Invalid job identifiers.
- Application history retrieval.
- Application status display.
- Session validation.
- Database error handling.

## 10. Outcome

This module connects job discovery with the application process
and provides the foundation for application tracking.

## 11. Technologies Used

- Java
- Java Servlets
- JSP
- JDBC
- MySQL
- Maven
- Apache Tomcat
- HTML/CSS