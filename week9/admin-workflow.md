# Admin Workflow

## 1. Admin Login Flow

Administrator
      |
      v
Login Page
      |
      v
Authentication Servlet
      |
      v
Validate Credentials and Role
      |
      v
Create Authenticated Session
      |
      v
Admin Dashboard

## 2. User Management Flow

Admin Dashboard
      |
      v
User Management
      |
      v
Admin Servlet
      |
      v
Admin DAO
      |
      v
MySQL Database
      |
      v
Retrieve User Records
      |
      v
Display User Information

## 3. Recruiter Approval Flow

Admin Dashboard
      |
      v
Pending Recruiters
      |
      v
Review Recruiter Account
      |
      v
Validate Administrative Request
      |
      v
Update Account Status
      |
      v
Display Result

## 4. Authorization

Every administrative request must verify that the current
session belongs to an authorized administrator.

The server must not trust a role supplied by the browser.

## 5. Database Integration

The admin module uses the existing user, company, job, and
application tables as required by each operation.

No duplicate tables should be created for information already
stored in the database.

## 6. Error Handling

The system should handle missing records, invalid requests,
unauthorized access, and database failures without exposing
sensitive information.
