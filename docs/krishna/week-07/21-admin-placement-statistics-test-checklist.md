# Week 7 — Admin Placement Statistics Test Checklist

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 7

## 1. Objective

This checklist defines tests for verifying the correctness and reliability of placement statistics displayed on the administrator dashboard.

Tests should be marked as passed or failed only after execution against the actual application.

## 2. Environment Checks

- [ ] Verify that the application starts successfully.
- [ ] Verify that the database connection works.
- [ ] Confirm that the relevant database tables exist.
- [ ] Confirm that the dashboard route is available.
- [ ] Verify that an authorized administrator account is available.

## 3. Metric Accuracy Tests

- [ ] Compare Total Students with the corresponding database count.
- [ ] Compare Total Companies with the corresponding database count.
- [ ] Compare Total Jobs with the corresponding database count.
- [ ] Compare Total Internships with the corresponding database count.
- [ ] Compare Total Applications with the corresponding database count.
- [ ] Compare Selected Applications with the corresponding database count.
- [ ] Verify that each metric follows its documented counting rules.

## 4. Data Integrity Tests

- [ ] Check for duplicate counting caused by SQL joins.
- [ ] Verify that application statuses match the existing workflow.
- [ ] Confirm that closed and inactive postings are counted according to the defined rules.
- [ ] Verify that adding a valid record affects the relevant metric as expected.
- [ ] Verify that removing or changing a record affects the relevant metric as expected, where permitted.

## 5. Empty Database Tests

- [ ] Check dashboard behavior when no student records exist.
- [ ] Check dashboard behavior when no company records exist.
- [ ] Check dashboard behavior when no job postings exist.
- [ ] Check dashboard behavior when no internship postings exist.
- [ ] Check dashboard behavior when no applications exist.

## 6. Error Handling Tests

- [ ] Verify that database failures are handled appropriately.
- [ ] Check application logs for unexpected exceptions.
- [ ] Confirm that database credentials are not exposed.
- [ ] Verify that failed queries do not produce misleading success messages.
- [ ] Confirm that the dashboard remains usable when an error occurs, where supported.

## 7. Authorization Tests

- [ ] Verify that unauthenticated users cannot access protected administrator statistics.
- [ ] Verify that student accounts cannot access administrator-only statistics.
- [ ] Verify that recruiter accounts cannot access administrator-only statistics.
- [ ] Confirm that authorization is enforced on the server side.

## 8. Test Results

| Test Area | Status | Remarks |
|---|---|---|
| Student count | Not tested | Update after execution |
| Company count | Not tested | Update after execution |
| Job count | Not tested | Update after execution |
| Internship count | Not tested | Update after execution |
| Application count | Not tested | Update after execution |
| Selected application count | Not tested | Update after execution |
| Empty database handling | Not tested | Update after execution |
| Authorization | Not tested | Update after execution |

## 9. Defect Reporting

For each identified defect, record:

- Defect description.
- Steps to reproduce.
- Expected result.
- Actual result.
- Severity.
- Fix status.

## 10. Conclusion

This checklist provides a structured approach to testing placement statistics.

The results should be updated after executing the tests against the actual project.

## Commit Message

test: add admin placement statistics test checklist
