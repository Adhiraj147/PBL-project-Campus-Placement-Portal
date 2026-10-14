# Week 10 - System Testing

## Testing Objective

To verify the major workflows of the Campus Placement and
Internship Portal from the user's perspective.

## Test Cases

| ID | Test Scenario | Expected Result | Status |
|---|---|---|---|
| TC01 | Register a new student | Student account is created | Not Tested |
| TC02 | Register a new recruiter | Recruiter account is created | Not Tested |
| TC03 | Register using an existing email | Duplicate registration is rejected | Not Tested |
| TC04 | Login with valid credentials | Correct dashboard opens | Not Tested |
| TC05 | Login with invalid credentials | Error message is displayed | Not Tested |
| TC06 | Open a protected page without login | Login is required | Not Tested |
| TC07 | Student opens available jobs | Job listings are displayed | Not Tested |
| TC08 | Student applies for a job | Application is recorded | Not Tested |
| TC09 | Student applies for the same job again | Duplicate application is prevented | Not Tested |
| TC10 | Student opens application history | Application records are displayed | Not Tested |
| TC11 | Recruiter views applicants | Authorized applicants are displayed | Not Tested |
| TC12 | Recruiter accesses another company's applicants | Access is denied | Not Tested |
| TC13 | Admin opens the admin dashboard | Authorized admin dashboard opens | Not Tested |
| TC14 | Student attempts admin access | Access is denied | Not Tested |
| TC15 | Database connection fails | Error is handled safely | Not Tested |
| TC16 | Invalid request parameters are submitted | Request is handled safely | Not Tested |

## Testing Procedure

1. Start MySQL.
2. Start the Java web application.
3. Open the application in a browser.
4. Test each scenario using the relevant account.
5. Verify database changes in MySQL Workbench when appropriate.
6. Record the actual result.
7. Mark the test as PASS or FAIL.

## Result Recording

Use the following status values:

- PASS: The actual result matches the expected result.
- FAIL: The actual result differs from the expected result.
- NOT TESTED: The test has not yet been executed.
- NOT IMPLEMENTED: The required feature does not exist.

## Conclusion

System testing provides evidence that the implemented
application workflows behave as expected.