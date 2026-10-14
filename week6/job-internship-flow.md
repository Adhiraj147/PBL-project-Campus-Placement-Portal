# Job and Internship Module Flow

## Overview

The Job and Internship Module connects the student interface with placement and internship opportunities stored in the database.

## Flow

```text
Student
   |
   ↓
Student Dashboard
   |
   ↓
Jobs / Internships
   |
   ↓
JobListServlet
   |
   ↓
JobDAO
   |
   ↓
JDBC
   |
   ↓
MySQL
   |
   ↓
Job Records
   |
   ↓
JobListServlet
   |
   ↓
jobs.jsp
   |
   ↓
Student


Student
   |
   ↓
Select Job
   |
   ↓
JobDetailsServlet
   |
   ↓
JobDAO
   |
   ↓
Database
   |
   ↓
Selected Job
   |
   ↓
job-details.jsp