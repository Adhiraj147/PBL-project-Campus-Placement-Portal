# Applicant Management Workflow

## 1. Purpose

This document describes how recruiters access and manage student
applications in the Campus Placement and Internship Portal.

## 2. Recruiter Workflow

Recruiter Login
      |
      v
Authentication
      |
      v
Recruiter Dashboard
      |
      v
View Posted Jobs
      |
      v
Select Job
      |
      v
View Applicants
      |
      v
Retrieve Applications
      |
      v
Display Applicant Information
      |
      v
Review Application
      |
      v
Update Application Status
      |
      v
Save Changes

## 3. Database Interaction

The application retrieval process uses the existing database
relationships between:

- Users
- Companies
- Jobs
- Applications
- Students

The exact table and column names must match the project's schema.

## 4. Authorization

The backend must verify that the authenticated recruiter is
authorized to manage the selected job.

The system must not rely only on a job ID supplied by the browser.

## 5. Application Status Updates

If status updates are implemented, the backend should:

1. Validate the requested status.
2. Verify the recruiter session.
3. Verify ownership of the associated job.
4. Update the relevant application.
5. Display the result to the recruiter.

## 6. Error Handling

The application should handle:

- Missing job identifiers.
- Invalid job identifiers.
- Jobs belonging to another company.
- Applications that do not exist.
- Unauthorized requests.
- Database errors.
- Empty applicant lists.