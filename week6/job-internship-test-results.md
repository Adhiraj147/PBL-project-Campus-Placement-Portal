# Week 6 - Job and Internship Test Results

## Testing Objective

The objective was to verify that job and internship opportunities can be retrieved from the database and displayed correctly to students.

## Test Cases

| Test Case | Expected Result | Status |
|---|---|---|
| Open Jobs & Internships page | Page loads successfully | PASS |
| Active jobs exist | Jobs are displayed | PASS |
| No active jobs exist | Appropriate message is displayed | PASS |
| Select a job | Job details page opens | PASS |
| Valid Job ID | Correct job details displayed | PASS |
| Invalid Job ID | Appropriate error handling | PASS |
| Invalid Job ID format | User is redirected safely | PASS |
| Database unavailable | Application handles database error | PASS |
| JSP navigation | Correct page opens | PASS |

## Database Testing

The Job DAO was tested to verify that job records are correctly retrieved using JDBC.

## Functional Testing

The complete flow was tested from the student dashboard to the jobs/internships section.

## Final Result

The Job and Internship Management Module successfully retrieves and displays available opportunities for students.