# Admin & Analytics — Week 1 Test Checklist

**Contributor:** Krishna
**Project:** Campus Placement and Internship Portal
**Week:** 1

## 1. Objective

The objective of this checklist is to verify the existing administrator dashboard and prepare it for further development.

Each test should be marked as passed or failed only after it has actually been performed.

## 2. Environment Verification

- [ ] Verify that the project builds successfully.
- [ ] Verify that MySQL is running.
- [ ] Verify that the project database has been created.
- [ ] Verify that the database schema has been imported.
- [ ] Verify that the database connection settings are correct.
- [ ] Verify that the application starts successfully on Tomcat.
- [ ] Verify that an authorized administrator account is available.

## 3. Dashboard Verification

- [ ] Open the administrator dashboard after signing in.
- [ ] Verify that the dashboard loads without a server error.
- [ ] Verify that the Total Students statistic is displayed.
- [ ] Verify that the Total Companies statistic is displayed.
- [ ] Verify that the Total Jobs statistic is displayed.
- [ ] Verify that the Total Internships statistic is displayed.
- [ ] Verify that the Total Applications statistic is displayed.
- [ ] Verify that the selected-application statistic is displayed wherever it is used.
- [ ] Compare the displayed statistics with the corresponding database counts.

## 4. Navigation Verification

- [ ] Check the Dashboard navigation link.
- [ ] Check the Manage Students navigation link.
- [ ] Check the Manage Companies navigation link.
- [ ] Check the System Reports navigation link.
- [ ] Record any navigation links that do not lead to implemented pages.

## 5. Access Control Verification

- [ ] Verify that an unauthenticated user cannot access protected admin functionality.
- [ ] Verify that a student account cannot access admin-only functionality.
- [ ] Verify that a recruiter account cannot access admin-only functionality.
- [ ] Confirm that administrative operations require appropriate authorization.

These tests must be performed against the application's actual authentication and authorization configuration.

## 6. Error Handling Verification

- [ ] Check the application logs for unexpected exceptions.
- [ ] Verify that database failures do not expose database credentials.
- [ ] Verify that the application handles dashboard loading errors appropriately.
- [ ] Record any errors that require correction.

## 7. Test Results

| Test | Result | Remarks |
|------|--------|---------|
| Project startup | Not tested | Update after testing |
| Database connection | Not tested | Update after testing |
| Admin dashboard | Not tested | Update after testing |
| Dashboard statistics | Not tested | Update after testing |
| Navigation links | Not tested | Update after testing |
| Access control | Not tested | Update after testing |
| Error handling | Not tested | Update after testing |

## 8. Follow-Up Tasks

The following tasks may be taken up in subsequent weeks:

1. Implement student management functionality.
2. Implement company management and verification.
3. Implement job and internship moderation.
4. Implement application monitoring.
5. Improve placement statistics.
6. Develop administrative reports.
7. Test the completed features.

## 9. Conclusion

This checklist provides a structured approach to verifying the existing administrator dashboard.

The results will help identify problems and guide the development of additional administrative features.

Testing results must be updated after executing the tests in the actual project environment.
