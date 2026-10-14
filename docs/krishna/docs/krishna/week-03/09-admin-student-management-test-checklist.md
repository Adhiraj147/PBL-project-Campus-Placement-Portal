# Week 3 — Admin Student Management Test Checklist

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics

## 1. Objective

The objective of this checklist is to verify the correctness, usability, and security of student management functionality available to administrators.

Tests should be marked as passed or failed only after they have actually been executed.

## 2. Environment Checks

- [ ] Verify that the application starts successfully.
- [ ] Verify that the database connection works.
- [ ] Verify that the required student tables are available.
- [ ] Verify that an authorized administrator account is available.
- [ ] Confirm that the relevant student-management route or page exists.

## 3. Student Listing Tests

- [ ] Open the student management page as an authorized administrator.
- [ ] Verify that student records are displayed when records exist.
- [ ] Verify that the page handles an empty student list.
- [ ] Compare displayed student information with the database.
- [ ] Verify that the page handles database errors appropriately.

## 4. Search and Filtering Tests

If search or filtering functionality is implemented:

- [ ] Search for an existing student.
- [ ] Search for a student who does not exist.
- [ ] Test empty search input.
- [ ] Test input containing special characters.
- [ ] Verify that the displayed results match the search criteria.
- [ ] Verify that search input is handled safely.

## 5. Record Validation Tests

If student record management actions are implemented:

- [ ] Verify the student identifier.
- [ ] Test required-field validation.
- [ ] Test invalid email input where applicable.
- [ ] Test duplicate values where uniqueness is required.
- [ ] Test an operation referencing a nonexistent student.
- [ ] Verify that invalid input does not cause unintended database changes.

## 6. Authorization Tests

- [ ] Verify that an unauthenticated user cannot access protected student-management operations.
- [ ] Verify that a student account cannot perform administrator-only operations.
- [ ] Verify that a recruiter account cannot perform administrator-only operations.
- [ ] Verify that server-side authorization is enforced.

## 7. Database Integrity Tests

- [ ] Verify that student records remain consistent after permitted operations.
- [ ] Verify that related application records are not unintentionally affected.
- [ ] Verify that database constraints are respected.
- [ ] Verify that failed operations are handled safely.

## 8. Test Results

| Test Area | Status | Remarks |
|---|---|---|
| Application startup | Not tested | Update after execution |
| Student listing | Not tested | Update after execution |
| Search and filtering | Not tested | Update after execution |
| Record validation | Not tested | Update after execution |
| Authorization | Not tested | Update after execution |
| Database integrity | Not tested | Update after execution |

## 9. Defect Reporting

For each identified defect, record:

- Defect description.
- Steps to reproduce.
- Expected result.
- Actual result.
- Severity.
- Fix status.

## 10. Conclusion

This checklist provides a structured method for testing administrator-facing student management functionality.

The test results should be updated after execution against the actual project.

Commit message - test: add admin student management test checklist
