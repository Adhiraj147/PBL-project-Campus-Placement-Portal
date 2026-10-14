# Week 6 — Admin Application Monitoring Test Checklist

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 6

## 1. Objective

This checklist defines the tests required to verify application monitoring and status tracking functionality in the administrator module.

Tests should be marked as passed or failed only after they have been executed against the actual application.

## 2. Environment Checks

- [ ] Verify that the application starts successfully.
- [ ] Verify that the database connection works.
- [ ] Confirm that the application table exists.
- [ ] Confirm that the relevant student and opportunity tables exist.
- [ ] Verify that an authorized administrator account is available.
- [ ] Confirm that the monitoring page or route exists.

## 3. Application Listing Tests

- [ ] Open the application monitoring page as an authorized administrator.
- [ ] Verify that existing application records are displayed.
- [ ] Verify that an empty application list is handled correctly.
- [ ] Compare displayed application records with database records.
- [ ] Verify that student and opportunity information is associated correctly.
- [ ] Check for duplicate application rows.
- [ ] Verify that database errors are handled appropriately.

## 4. Status Tracking Tests

- [ ] Confirm the status values supported by the project.
- [ ] Verify that each displayed status matches its database value.
- [ ] Check how missing or unsupported status values are handled.
- [ ] Verify that refreshing the page does not change stored application statuses.
- [ ] Confirm that monitoring operations do not unintentionally modify records.

## 5. Filtering Tests

If application filtering has been implemented:

- [ ] Filter applications by status.
- [ ] Filter applications by company where supported.
- [ ] Filter applications by opportunity where supported.
- [ ] Search using a valid student identifier.
- [ ] Search using an identifier that does not exist.
- [ ] Test empty search input.
- [ ] Verify that filter results match the requested criteria.

## 6. Authorization Tests

- [ ] Verify that unauthenticated users cannot access protected monitoring functionality.
- [ ] Verify that student accounts cannot access administrator-only monitoring pages.
- [ ] Verify that recruiter accounts cannot access administrator-only monitoring pages.
- [ ] Confirm that authorization is enforced on the server side.

## 7. Data Integrity Tests

- [ ] Verify that application records remain unchanged during read-only monitoring.
- [ ] Confirm that related student records are not unintentionally modified.
- [ ] Confirm that related opportunity records are not unintentionally modified.
- [ ] Verify that invalid requests are handled safely.
- [ ] Check that database constraints are respected.

## 8. Test Results

| Test Area | Status | Remarks |
|---|---|---|
| Application listing | Not tested | Update after execution |
| Student and opportunity relationships | Not tested | Update after execution |
| Status accuracy | Not tested | Update after execution |
| Filtering | Not tested | Update after execution |
| Authorization | Not tested | Update after execution |
| Data integrity | Not tested | Update after execution |
| Error handling | Not tested | Update after execution |

## 9. Defect Reporting

For each identified defect, record:

- Defect description.
- Steps to reproduce.
- Expected result.
- Actual result.
- Severity.
- Fix status.

## 10. Conclusion

This checklist provides a structured method for verifying application monitoring and status tracking.

The results should be updated after testing the actual implementation.

## Commit Message

test: add admin application monitoring test checklist
