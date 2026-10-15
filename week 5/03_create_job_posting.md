# Week 5 – Create Job Posting

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

The objective of Week 5 is to implement the Create Job Posting functionality for recruiters.

The feature allows an authenticated recruiter to enter information about a job opportunity and submit it through the Recruiter Portal.

---

## 2. Job Posting Information

The job posting form collects:

- Job title
- Job description
- Job location
- Eligibility requirements
- Salary/package
- Application deadline

---

## 3. Job Creation Workflow

Recruiter
    |
    v
Recruiter Dashboard
    |
    v
Create Job Posting
    |
    v
Enter Job Details
    |
    v
Validate Information
    |
    v
Submit Form
    |
    v
Process Request
    |
    v
Store Job Information
    |
    v
Job Posting Created

---

## 4. Authentication

Only an authenticated recruiter should be allowed to create a job posting.

The servlet checks the active user session before processing the form.

If the recruiter is not logged in, the request is redirected to the login page.

---

## 5. Validation

The following fields are validated:

### Job Title

The job title is required.

### Job Description

The job description is required.

### Other Information

Location, eligibility, salary and deadline are accepted as additional job information.

Input values are trimmed before processing.

---

## 6. User Interface

The Job Posting page contains:

- Job title input
- Job description text area
- Location input
- Eligibility text area
- Salary/package input
- Application deadline
- Create Job Posting button

---

## 7. Servlet Responsibilities

The OpportunityServlet is responsible for:

1. Checking recruiter authentication.
2. Displaying the job posting form.
3. Reading submitted form information.
4. Validating required fields.
5. Processing the job posting request.
6. Returning success or error information.

---

## 8. Database Integration

The validated job information should be passed to the existing DAO/database layer.

The general architecture is:

JSP
 |
 v
OpportunityServlet
 |
 v
OpportunityDAO
 |
 v
MySQL Database

The DAO layer should handle database-specific operations.

---

## 9. Testing

### Test Case 1

Enter valid job information.

Expected result:

Job posting should be created successfully.

### Test Case 2

Leave job title empty.

Expected result:

Validation error should be displayed.

### Test Case 3

Leave job description empty.

Expected result:

Validation error should be displayed.

### Test Case 4

Try to access the page without login.

Expected result:

User should be redirected to the login page.

### Test Case 5

Enter optional information such as location and salary.

Expected result:

Information should be accepted and processed.

---

## 10. Future Enhancements

The job posting module will later support:

- Viewing job postings
- Editing job postings
- Deleting job postings
- Closing job postings
- Managing applicants for each job

---

## 11. Week 5 Outcome

The Create Job Posting feature was implemented for the Recruiter & Company Portal.

Recruiters can enter job information through a dedicated form, while the servlet performs authentication and basic validation before processing the request.