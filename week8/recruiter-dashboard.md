# Week 8 - Recruiter Dashboard and Applicant Management

## 1. Overview

During Week 8, I focused on the Recruiter Dashboard and Applicant
Management functionality of the Campus Placement and Internship Portal.

The purpose of this module is to help recruiters access recruitment
information and review student applications.

## 2. Objectives

The objectives of this module are:

- Provide a dedicated recruiter dashboard.
- Retrieve applications associated with recruiter job postings.
- Display applicant information.
- Show job and internship details.
- Allow recruiters to review submitted applications.
- Support application status updates where implemented.
- Integrate recruiter functionality with the existing authentication system.
- Validate recruiter access to protected pages.

## 3. Main Components

The module may include:

1. Recruiter Dashboard
2. Applicant Listing
3. Application DAO
4. Applicant Retrieval Queries
5. Application Status Management
6. Recruiter JSP Pages
7. Database Integration

## 4. Recruiter Dashboard

The recruiter dashboard acts as the central interface for recruitment activities.

Depending on the implemented features, it can provide access to:

- Posted jobs and internships.
- Applications received.
- Applicant details.
- Application statuses.
- Recruitment activity summaries.

## 5. Applicant Management

Recruiters need to view applications submitted for opportunities
associated with their company.

The system should ensure that recruiters can access only the
applications belonging to jobs they are authorized to manage.

## 6. Application Status

The application status indicates the current stage of a student's application.

Supported statuses depend on the project implementation.

Examples include:

- APPLIED
- UNDER_REVIEW
- SHORTLISTED
- REJECTED
- SELECTED

## 7. Security

Recruiter pages should require an authenticated recruiter session.

Server-side authorization must verify that the recruiter owns
or is authorized to manage the relevant job posting.

A job ID supplied through a browser request must not be treated
as proof of ownership.

## 8. Testing

Testing should cover:

- Recruiter login.
- Recruiter dashboard access.
- Applicant retrieval.
- Job and applicant association.
- Application status updates.
- Unauthorized access attempts.
- Database error handling.

## 9. Outcome

The recruiter-side workflow connects recruiters with student
applications and supports the management of recruitment activities.

## 10. Technologies Used

- Java
- Servlets
- JSP
- JDBC
- MySQL
- Maven
- Apache Tomcat
- HTML/CSS