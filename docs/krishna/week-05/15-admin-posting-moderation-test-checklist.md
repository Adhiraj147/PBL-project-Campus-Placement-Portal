# Week 5 — Admin Posting Moderation Test Checklist

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 5

## 1. Objective

This checklist defines the tests required to verify administrator-facing job and internship moderation functionality.

Tests should be marked as passed or failed only after they have been executed against the actual application.

## 2. Environment Checks

- [ ] Verify that the application starts successfully.
- [ ] Verify that the database connection works.
- [ ] Confirm that the relevant job and internship tables exist.
- [ ] Verify that an authorized administrator account is available.
- [ ] Confirm that the relevant moderation page or route exists.

## 3. Job Moderation Tests

If job moderation has been implemented:

- [ ] Open the job moderation page as an administrator.
- [ ] Verify that existing job postings are displayed.
- [ ] Open a job posting and review its details.
- [ ] Test an allowed moderation action.
- [ ] Verify that the updated status is saved.
- [ ] Reload the page and confirm that the status persists.
- [ ] Test an invalid job identifier.
- [ ] Test an unsupported status value.
- [ ] Verify that failed operations do not report false success.

## 4. Internship Moderation Tests

If internship moderation has been implemented:

- [ ] Open the internship moderation page.
- [ ] Verify that existing internship postings are displayed.
- [ ] Review the available internship details.
- [ ] Test an allowed moderation action.
- [ ] Verify that the updated status is saved.
- [ ] Reload the page and confirm that the status persists.
- [ ] Test an invalid internship identifier.
- [ ] Test an unsupported status value.
- [ ] Verify that database errors are handled appropriately.

## 5. Authorization Tests

- [ ] Verify that unauthenticated users cannot access protected moderation operations.
- [ ] Verify that student accounts cannot perform administrator-only moderation actions.
- [ ] Verify that recruiter accounts cannot perform administrator-only moderation actions.
- [ ] Confirm that authorization is checked on the server side.

## 6. Data Integrity Tests

- [ ] Verify that successful status updates persist in the database.
- [ ] Verify that unrelated posting fields remain unchanged.
- [ ] Confirm that invalid requests do not produce unintended updates.
- [ ] Verify that existing database constraints are respected.
- [ ] Check that concurrent or repeated requests are handled appropriately where relevant.

## 7. Error Handling Tests

- [ ] Test requests containing invalid identifiers.
- [ ] Test unsupported moderation actions.
- [ ] Check application logs for unexpected exceptions.
- [ ] Verify that database errors do not expose credentials.
- [ ] Confirm that the user receives appropriate error feedback.

## 8. Test Results

| Test Area | Status | Remarks |
|---|---|---|
| Job listing | Not tested | Update after execution |
| Job moderation | Not tested | Update after execution |
| Internship listing | Not tested | Update after execution |
| Internship moderation | Not tested | Update after execution |
| Authorization | Not tested | Update after execution |
| Data integrity | Not tested | Update after execution |
| Error handling | Not tested | Update after execution |

## 9. Defect Reporting

For every identified defect, record:

- Defect description.
- Steps to reproduce.
- Expected result.
- Actual result.
- Severity.
- Fix status.

## 10. Conclusion

This checklist provides a structured approach to testing job and internship moderation functionality.

Test results should be updated after execution against the actual project.

## Commit Message

test: add admin posting moderation test checklist
