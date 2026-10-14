# Admin Module Integration Dependencies

## 1. Purpose

This document records the dependencies that should be checked while integrating the Admin Module with the Campus Placement and Internship Portal.

The exact dependencies must be confirmed from the existing source code and database schema.

## 2. Authentication and Authorization

Required checks:

- Admin login flow
- Session creation and validation
- Authorization before administrative operations
- Logout and session invalidation
- Access restrictions for non-admin users

The integration must use the project's existing authentication design where possible.

## 3. Dashboard Dependencies

The admin dashboard may depend on:

- Dashboard JSP or equivalent view
- Dashboard servlet or controller
- DAO or service methods
- Database queries for summary statistics

Verify that displayed totals come from actual database records.

## 4. Student Management Dependencies

Potential dependencies include:

- Student listing page
- Student-related servlet or controller
- Student DAO methods
- Student table and related records

Verify that the available management operations match the existing implementation.

## 5. Company Management Dependencies

Potential dependencies include:

- Company listing page
- Company verification workflow
- Company-related servlet or controller
- Company DAO methods
- Company table and verification-status fields

Verify that status changes are saved correctly and displayed consistently.

## 6. Job and Internship Moderation Dependencies

Potential dependencies include:

- Job and internship listing pages
- Posting moderation operations
- Posting DAO methods
- Posting records and moderation-status fields

Verify that the supported status values match the database and application logic.

## 7. Application Monitoring Dependencies

Potential dependencies include:

- Application listing page
- Application status display
- Application DAO methods
- Student, posting, and application relationships

Verify that application totals are not confused with unique student totals.

## 8. Reports and Analytics Dependencies

Potential dependencies include:

- Report pages
- Report servlet or controller
- Queries or DAO methods for report data
- Reliable date and status fields

Verify every report metric against known test records before marking it as correct.

## 9. Shared Configuration

Check:

- Java version compatibility
- Web application configuration
- Servlet and JSP configuration
- Database connection settings
- Required libraries
- Server configuration
- Context path and URL mappings

Do not add a new dependency unless the existing project requires it.

## 10. Dependency Verification Table

| Area | Dependency to Verify | Status |
|---|---|---|
| Authentication | Admin session and authorization | Not verified |
| Dashboard | Summary data source | Not verified |
| Students | Student DAO and records | Not verified |
| Companies | Company DAO and verification fields | Not verified |
| Moderation | Posting status and update logic | Not verified |
| Applications | Application records and statuses | Not verified |
| Reports | Metric queries and date fields | Not verified |
| Configuration | Java, server, and database settings | Not verified |

## 11. Current Status

Status: Dependency checklist documented. Each dependency must be confirmed against the actual project before integration is considered complete.

## Commit Message

`docs: document admin integration dependencies`
