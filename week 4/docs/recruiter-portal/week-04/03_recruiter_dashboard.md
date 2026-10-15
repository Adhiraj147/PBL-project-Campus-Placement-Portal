# Week 4 – Recruiter Dashboard

## Student

Saurabh Gupta

## Project

Campus Placement and Internship Portal

## Module

Recruiter & Company Portal

## Branch

feature/saurabh

---

## 1. Objective

The objective of Week 4 is to create the Recruiter Dashboard for the Recruiter & Company Portal.

The dashboard provides a central interface from which recruiters can access company profile, job posting, internship and applicant-related functionality.

---

## 2. Dashboard Purpose

The Recruiter Dashboard acts as the main entry point after recruiter authentication.

It provides:

- Recruiter welcome section
- Job statistics
- Internship statistics
- Applicant statistics
- Shortlisted applicant statistics
- Company Profile navigation
- Job creation navigation
- Internship navigation
- Applicant navigation
- Logout option

---

## 3. Dashboard Workflow

Recruiter Login
       |
       v
Authentication
       |
       v
Recruiter Dashboard
       |
       +---- Company Profile
       |
       +---- Create Job
       |
       +---- Create Internship
       |
       +---- View Applicants
       |
       +---- Logout

---

## 4. Authentication Check

The dashboard verifies whether the user has an active session.

If the session does not contain the required user information, the user is redirected to the login page.

This prevents unauthenticated users from directly accessing the recruiter dashboard.

---

## 5. Dashboard Statistics

The dashboard contains four main statistics:

### Total Jobs

Displays the number of job postings created by the recruiter/company.

### Total Internships

Displays the number of internship postings.

### Total Applicants

Displays the number of applicants associated with recruiter opportunities.

### Shortlisted Applicants

Displays the number of applicants who have been shortlisted.

---

## 6. Recruiter Actions

The dashboard provides navigation to recruiter functionality.

### Company Profile

Allows the recruiter to access company information.

### Create Job

Allows the recruiter to create a new job opportunity.

### Create Internship

Provides access to internship creation.

### View Applicants

Provides access to applicant management.

---

## 7. Files Added/Modified

### RecruiterDashboardServlet.java

Responsible for:

- Checking recruiter session
- Preparing dashboard information
- Forwarding the request to the dashboard JSP

### recruiter-dashboard.jsp

Responsible for:

- Dashboard user interface
- Statistics cards
- Recruiter navigation
- Company profile navigation
- Job posting navigation
- Applicant navigation

---

## 8. Future Database Integration

The initial dashboard contains the structure required for displaying recruiter statistics.

The statistics can later be connected with the database and DAO layer.

For example:

Database
   |
   v
Opportunity DAO
   |
   v
Recruiter Dashboard Servlet
   |
   v
Dashboard JSP

This allows the dashboard to display real-time counts.

---

## 9. Testing

The following cases should be tested:

1. Login as recruiter.
2. Open recruiter dashboard.
3. Verify dashboard loads successfully.
4. Verify company profile link.
5. Verify create job link.
6. Verify internship navigation.
7. Verify applicant navigation.
8. Try opening dashboard without login.
9. Verify unauthenticated user is redirected.
10. Verify logout functionality.

---

## 10. Expected Result

After successful recruiter authentication, the recruiter should be able to access a centralized dashboard.

The dashboard should provide quick access to the main Recruiter & Company Portal features.

---

## 11. Week 4 Outcome

The Recruiter Dashboard interface and servlet workflow were implemented.

The dashboard provides the foundation for connecting the recruiter with company profile, job posting, internship posting and applicant management functionality.