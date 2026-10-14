# Week 4 — Admin Company Management Test Checklist

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 4

## 1. Objective

This checklist defines tests for administrator-facing company management and company verification functionality.

Mark a test as passed or failed only after executing it against the actual application.

## 2. Environment Checks

- [ ] Verify that the application starts successfully.
- [ ] Verify that the database connection works.
- [ ] Verify that the required company table exists.
- [ ] Confirm that an authorized administrator account is available.
- [ ] Confirm that the relevant company management route or page exists.

## 3. Company Listing Tests

- [ ] Open the company management page as an authorized administrator.
- [ ] Verify that existing company records are displayed.
- [ ] Verify that the page handles an empty company list.
- [ ] Compare displayed company information with the database.
- [ ] Verify that database errors are handled appropriately.

## 4. Verification Workflow Tests

If company verification is implemented:

- [ ] Open a company with a pending verification request.
- [ ] Review the available company information.
- [ ] Test an allowed verification action.
- [ ] Verify that the updated status is saved.
- [ ] Reload the page and confirm that the status persists.
- [ ] Test an unsupported status value.
- [ ] Test an update for a nonexistent company.
- [ ] Verify that a failed update does not report false success.

## 5. Authorization Tests

- [ ] Verify that unauthenticated users cannot access protected administrative operations.
- [ ] Verify that students cannot perform company verification actions.
- [ ] Verify that recruiters cannot perform administrator-only verification actions.
- [ ] Confirm that authorization is enforced on the server side.

## 6. Database Integrity Tests

- [ ] Verify that valid company updates persist.
- [ ] Verify that unrelated company fields are not unintentionally modified.
- [ ] Verify that related job and internship records remain consistent.
- [ ] Verify that database constraints are respected.
- [ ] Verify that failed operations are handled safely.

## 7. Error Handling Tests

- [ ] Test invalid company identifiers.
- [ ] Test invalid verification status values.
- [ ] Check application logs for unexpected exceptions.
- [ ] Confirm that error messages do not expose credentials or sensitive internal details.

## 8. Test Results

| Test Area | Status | Remarks |
|---|---|---|
| Company listing | Not tested | Update after execution |
| Company details | Not tested | Update after execution |
| Verification workflow | Not tested | Update after execution |
| Authorization | Not tested | Update after execution |
| Database integrity | Not tested | Update after execution |
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

This checklist provides a structured approach to verifying company management and verification functionality.

The checklist should be updated after testing the actual implementation.

## Commit Message

test: add admin company management test checklist
