# Week 8 - Recruiter and Applicant Testing

## Testing Objective

The objective is to verify that recruiters can access authorized
applicant information and manage applications securely.

## Test Cases

| ID | Test Case | Expected Result | Status |
|---|---|---|---|
| TC01 | Recruiter logs in | Recruiter dashboard opens | Pending |
| TC02 | Recruiter opens applicant list | Authorized applicants appear | Pending |
| TC03 | Job has no applicants | Empty-state message appears | Pending |
| TC04 | Invalid job ID is provided | Request is handled safely | Pending |
| TC05 | Recruiter accesses another company's job | Access is denied | Pending |
| TC06 | Unauthenticated user opens recruiter page | Login is required | Pending |
| TC07 | Authorized recruiter updates a status | Valid update is saved | Pending |
| TC08 | Invalid application status is submitted | Update is rejected | Pending |
| TC09 | Database error occurs | Error is handled safely | Pending |

## Testing Notes

Execute each test against the running application.

Replace Pending with PASS or FAIL after testing.

If a feature is not implemented, mark the corresponding test
as Not Implemented rather than reporting it as passed.

## Final Result

The module will be considered complete when the implemented
recruiter workflows pass their functional and authorization tests.