# Week 5 - Student Module Test Results

## Testing Objective

The objective of testing was to verify that the Student Module correctly works with the authentication system and database.

## Test Cases

| Test Case | Expected Result | Status |
|---|---|---|
| Student logs in with valid credentials | Student dashboard opens | PASS |
| Invalid login credentials | Login fails | PASS |
| Student session is available | Student information is retrieved | PASS |
| Student profile exists | Profile information is displayed | PASS |
| Student profile does not exist | Application handles the situation | PASS |
| Direct dashboard access without login | User is redirected to login | PASS |
| Dashboard JSP loads correctly | Dashboard is displayed | PASS |

## Issue Found During Development

During development, a case was encountered where the Student object could be null.

Directly accessing the student ID in this situation could result in:

`NullPointerException`

The dashboard flow was improved by checking whether the Student object exists before accessing its properties.

## Final Result

The Student Module was integrated successfully with the existing authentication system.

The student can log in and access the student dashboard through the authenticated session.