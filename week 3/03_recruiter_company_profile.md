# Week 3 – Recruiter Company Profile

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

The objective of Week 3 is to implement the Company Profile functionality for recruiters.

The Company Profile allows a recruiter to manage basic company information that can later be used with job postings, internship postings and applicant management.

---

## 2. Features Implemented

The following functionality was added:

- Recruiter company profile page
- Company name field
- Company description field
- Company location field
- Company website field
- Profile validation
- Recruiter session verification
- Profile update request handling
- Success and error messages

---

## 3. Files Modified

### RecruiterProfileServlet.java

The servlet handles:

- Recruiter session verification
- GET request for opening the profile
- POST request for updating profile information
- Basic validation
- Processing profile form data

### recruiter-profile.jsp

The JSP provides the user interface for:

- Viewing the company profile form
- Entering company information
- Editing company information
- Displaying success messages
- Displaying validation errors

---

## 4. Profile Fields

The company profile contains:

| Field | Purpose |
|------|---------|
| Company Name | Identifies the company |
| Company Description | Describes the company |
| Location | Stores company location |
| Website | Stores company website |

---

## 5. Security

The profile servlet checks whether a recruiter has an active login session before allowing access.

If the user is not logged in, the request is redirected to the login page.

---

## 6. Validation

The profile form validates required information.

Company name is required.

The system also removes unnecessary leading and trailing spaces from the submitted information.

---

## 7. Workflow

Recruiter Login
       |
       v
Recruiter Dashboard
       |
       v
Company Profile
       |
       v
Enter Company Information
       |
       v
Submit Profile
       |
       v
Validate Information
       |
       v
Process Update
       |
       v
Display Success Message

---

## 8. Testing

The following cases should be tested:

1. Open profile while logged in.
2. Open profile without login.
3. Enter valid company name.
4. Submit empty company name.
5. Update company description.
6. Update company location.
7. Add company website.
8. Submit valid profile information.

---

## 9. Expected Result

The recruiter should be able to access the Company Profile page and enter or update company information.

The feature provides the foundation for future recruiter features such as job posting and internship posting.

---

## 10. Week 3 Outcome

The Recruiter Company Profile interface and controller workflow were implemented.

The feature improves the Recruiter Portal by providing a dedicated place for managing company information.