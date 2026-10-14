# Week 6 - Job and Internship Management Module

## Overview

During Week 6, I worked on the Job and Internship Management Module of the Campus Placement and Internship Portal.

The objective of this module is to allow students to view available placement and internship opportunities through the portal.

## Objectives

The main objectives of this week were:

- Develop job and internship listing functionality.
- Retrieve available opportunities from the database.
- Display job information to students.
- Implement DAO-based database access.
- Connect Java Servlets with JSP pages.
- Provide a structured interface for viewing opportunities.
- Test job and internship data retrieval.

## Module Components

The module consists of:

1. Job Model
2. Job DAO
3. Job DAO Implementation
4. Job Listing Servlet
5. Job Details Servlet
6. Job Listing JSP
7. Job Details JSP

## Job Information

The job/internship module can display information such as:

- Job title
- Company
- Job description
- Job type
- Location
- Salary/stipend
- Eligibility
- Application deadline
- Current status

## Working Flow

```text
Student Login
      ↓
Student Dashboard
      ↓
Jobs / Internships
      ↓
Job Listing Servlet
      ↓
Job DAO
      ↓
MySQL Database
      ↓
Available Opportunities
      ↓
JSP Page
      ↓
Student