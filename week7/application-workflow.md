# Student Application Workflow

## 1. Purpose

This document describes how a student application moves through
the Campus Placement and Internship Portal.

## 2. Application Submission Flow

Student
   |
   v
Student Dashboard
   |
   v
Job Listing
   |
   v
Select Job
   |
   v
Apply for Job
   |
   v
ApplyJobServlet
   |
   v
Validate Session and Job
   |
   v
ApplicationDAO
   |
   v
MySQL Database
   |
   v
Save Application
   |
   v
Display Result to Student

## 3. Application History Flow

Student
   |
   v
My Applications
   |
   v
MyApplicationsServlet
   |
   v
ApplicationDAO
   |
   v
Retrieve Applications
   |
   v
Display Application History

## 4. Session Validation

The application must identify the authenticated student before
accepting an application.

A user who is not logged in should be redirected to the login page.

The student ID should be obtained from trusted server-side
session/profile information rather than accepted blindly
from a form parameter.

## 5. Duplicate Application Handling

Before submitting an application, the system should verify
whether the student has already applied for the selected job.

A database uniqueness constraint should provide additional
protection against duplicate submissions.

## 6. Application Status

The system should display the current status associated with
each application.

Status changes should follow the workflow implemented by
the project.

## 7. Error Handling

The system should handle:

- Missing job ID.
- Invalid job ID.
- Unauthenticated requests.
- Duplicate applications.
- Database errors.
- Empty application history.