# Admin Dashboard — Baseline Review

**Contributor:** Krishna
**Project:** Campus Placement and Internship Portal
**Week:** 1

## 1. Introduction

The administrator dashboard is an important component of the Campus Placement and Internship Portal.

It provides an overview of placement-related information, including student registrations, companies, job opportunities, internships, and applications.

The purpose of this review is to understand the existing dashboard implementation and identify areas for future improvement.

## 2. Existing Components

The following components are involved in the administrator dashboard.

### 2.1 dashboard.jsp

This JSP file provides the dashboard interface.

Its responsibilities include:

- Displaying administrator dashboard information.
- Showing placement-related statistics.
- Providing navigation links to administrative sections.
- Presenting information received from the controller.

### 2.2 AdminDashboardServlet.java

This servlet handles requests for the administrator dashboard.

Its responsibilities include:

- Processing requests for the admin dashboard.
- Calling the AdminDAO layer.
- Retrieving system statistics.
- Passing the statistics to the JSP page.
- Forwarding the request to the dashboard view.

### 2.3 AdminDAO.java

This interface defines the database operations required by the administrator module.

It separates database access requirements from the controller implementation.

### 2.4 AdminDAOImpl.java

This class implements the database operations defined by AdminDAO.

The existing implementation retrieves system statistics from the database.

### 2.5 schema.sql

This file defines the database structure used by the project.

The relevant tables include students, companies, opportunities, and applications.

## 3. Dashboard Request Flow

The dashboard request follows these steps:

1. The administrator opens the dashboard URL.
2. AdminDashboardServlet receives the request.
3. The servlet calls AdminDAO to retrieve system statistics.
4. AdminDAOImpl executes the required database queries.
5. The retrieved statistics are returned to the servlet.
6. The servlet makes the statistics available to the JSP.
7. dashboard.jsp displays the dashboard information.

## 4. Existing Statistics

The existing DAO implementation retrieves the following statistics:

| Statistic | Purpose |
|-----------|---------|
| Total Students | Counts student records |
| Total Companies | Counts company records |
| Total Jobs | Counts job opportunities |
| Total Internships | Counts internship opportunities |
| Total Applications | Counts application records |
| Total Selected | Counts applications with SELECTED status |

These statistics help the administrator understand the current state of the placement portal.

## 5. Initial Observations

The existing architecture separates the presentation, controller, and database-access responsibilities.

Database queries are handled by the DAO implementation rather than directly inside the JSP.

The dashboard also contains navigation links for administrative sections such as student management, company management, and system reports.

Some navigation links may require functional routes and corresponding views.

## 6. Areas for Future Development

The following areas will be reviewed during subsequent weeks:

- Student management.
- Recruiter and company management.
- Company verification.
- Job and internship moderation.
- Application monitoring.
- Placement statistics.
- Reports and analytics.

The implementation status of these features must be verified before they are considered complete.

Commit Mesage -docs: add baseline review for admin dashboard
## 7. Conclusion

This review documents the existing dashboard architecture and its primary statistics.

The findings will be used as a reference for developing and improving the Admin & Analytics module in the following weeks.
