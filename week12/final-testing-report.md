# Final Testing Report
## Campus Placement and Internship Portal

### 1. Introduction

The purpose of this report is to document the final testing process
of the Campus Placement and Internship Portal.

The application is developed using Java, JSP, Servlets, JDBC,
MySQL, Maven, and Apache Tomcat.

Testing is performed to identify errors, verify major application
workflows, and ensure that the system behaves as expected.

### 2. Testing Objectives

- Verify student registration and login.
- Verify recruiter registration and login.
- Check student dashboard functionality.
- Verify job listing and job application workflows.
- Check recruiter dashboard and applicant management.
- Verify administrator access and management features.
- Check database connectivity and data operations.
- Identify and resolve errors discovered during testing.
- Confirm that the application starts and operates correctly.

### 3. Test Cases

| Test ID | Test Scenario | Expected Result | Actual Status |
|---------|---------------|-----------------|---------------|
| TC-01 | Open the application | Login page opens | Not Tested |
| TC-02 | Register a student | Student account is created | Not Tested |
| TC-03 | Register a recruiter | Recruiter account is created | Not Tested |
| TC-04 | Login with valid credentials | User reaches the appropriate dashboard | Not Tested |
| TC-05 | Login with invalid credentials | Appropriate error message is displayed | Not Tested |
| TC-06 | Open student dashboard | Student dashboard loads correctly | Not Tested |
| TC-07 | View available jobs | Job listings are displayed | Not Tested |
| TC-08 | Apply for a job | Application is recorded | Not Tested |
| TC-09 | View applicants as a recruiter | Relevant applicants are displayed | Not Tested |
| TC-10 | Update application status | Status is updated correctly | Not Tested |
| TC-11 | Access an admin page without authorization | Access is denied or redirected | Not Tested |
| TC-12 | Verify database connectivity | Database operations work correctly | Not Tested |
| TC-13 | Build the application using Maven | Build completes successfully | Not Tested |

### 4. Testing Procedure

1. Start the MySQL database service.
2. Verify that the project database exists.
3. Start the application using the configured development command.
4. Open the application in a browser.
5. Test registration and login for each supported user role.
6. Test the available dashboard features.
7. Verify job applications and recruiter workflows.
8. Check administrator functionality if implemented.
9. Review the application and server logs for errors.
10. Record the actual result of every test case.

### 5. Bug Tracking

Any issue discovered during testing should be documented with:

- Bug ID
- Description
- Steps to reproduce
- Expected result
- Actual result
- Fix applied
- Retest result

### 6. Final Result

The application will be considered ready for submission after
the implemented features have been tested and critical issues
have been resolved.

Only verified test results should be marked as PASS.

### 7. Conclusion

Final testing helps improve application reliability and confirms
that the implemented modules work together as expected.