# Admin Regression Testing Checklist — Week 10

## 1. Purpose

Regression testing checks whether existing functionality continues to work after a code change or bug fix.

Execute only the tests relevant to features available in the application.

## 2. Authentication

- [ ] Verify that valid administrator login works.
- [ ] Verify that invalid login attempts are rejected.
- [ ] Verify that protected pages require authorization.
- [ ] Verify that logout works correctly.
- [ ] Verify that expired sessions are handled appropriately.

## 3. Dashboard and Navigation

- [ ] Verify that the dashboard loads without unexpected errors.
- [ ] Verify that navigation links lead to the correct pages.
- [ ] Verify that displayed summary values match their defined data sources.
- [ ] Verify that empty datasets are handled correctly.

## 4. Student and Company Management

- [ ] Verify that student records are displayed correctly.
- [ ] Verify implemented student-management operations.
- [ ] Verify that company records are displayed correctly.
- [ ] Verify implemented company-verification operations.
- [ ] Verify that updates persist after refreshing the relevant page.

## 5. Posting Moderation

- [ ] Verify that job postings display the correct statuses.
- [ ] Verify that internship postings display the correct statuses.
- [ ] Verify that supported moderation actions update the correct record.
- [ ] Verify that invalid status values are rejected.

## 6. Application Monitoring

- [ ] Verify that application records display correctly.
- [ ] Verify that application statuses match the database.
- [ ] Verify that application counts are not confused with student counts.
- [ ] Verify that empty application results are handled appropriately.

## 7. Statistics and Reports

- [ ] Verify dashboard totals against known test data.
- [ ] Verify implemented report calculations.
- [ ] Verify supported date filters.
- [ ] Verify that percentage calculations handle zero values.
- [ ] Verify that report totals remain consistent with their defined filters.

## 8. Error and Input Handling

- [ ] Verify required-field validation.
- [ ] Verify invalid input handling.
- [ ] Verify missing-record handling.
- [ ] Verify that failed operations do not show false success messages.
- [ ] Verify that sensitive internal error details are not displayed.

## 9. Test Results

| Test ID | Result | Notes |
|---|---|---|
| REG-01 | Not tested | Authentication |
| REG-02 | Not tested | Authorization |
| REG-03 | Not tested | Dashboard |
| REG-04 | Not tested | Student management |
| REG-05 | Not tested | Company management |
| REG-06 | Not tested | Posting moderation |
| REG-07 | Not tested | Application monitoring |
| REG-08 | Not tested | Reports and statistics |
| REG-09 | Not tested | Validation and error handling |

## 10. Completion Criteria

Regression testing is complete when:

1. Relevant existing tests have been executed.
2. Failed tests have been investigated.
3. Confirmed defects have been fixed or documented as unresolved.
4. Fixes have been retested.
5. Results reflect actual test execution.

## 11. Current Status

Status: Regression checklist prepared. All tests remain Not tested until they are executed.

## Commit Message

`test: add admin regression testing checklist`
