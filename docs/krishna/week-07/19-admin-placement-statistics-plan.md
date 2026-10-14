# Week 7 — Admin Placement Statistics Plan

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 7

## 1. Objective

The objective of Week 7 is to plan placement statistics functionality for the administrator module.

Placement statistics help administrators understand recruitment activity and review the outcomes recorded in the portal.

## 2. Scope

The planned statistics include:

- Total registered students.
- Total registered companies.
- Total job postings.
- Total internship postings.
- Total applications.
- Total selected applications.
- Application status distribution, where supported.

The availability of each metric must be confirmed against the existing project database.

## 3. Existing Project Review

Before implementation, review:

- Existing database tables and relationships.
- AdminDAO.java and AdminDAOImpl.java.
- AdminDashboardServlet.java.
- dashboard.jsp.
- Existing application status values.
- Existing SQL queries used for dashboard statistics.

Reuse existing methods wherever possible and avoid duplicating functionality.

## 4. Proposed Statistics Workflow

1. The administrator opens the dashboard.
2. The servlet requests the required statistics.
3. The DAO retrieves data from the database.
4. The retrieved values are passed to the dashboard.
5. The dashboard displays the available statistics.
6. The values are verified against database results.

## 5. Data Accuracy

Each metric should have a clear definition.

For example, total applications should count application records rather than the number of students or job postings.

Selected applications should use the status value defined by the existing application workflow.

Queries should avoid duplicate counts caused by incorrect joins.

## 6. Performance Considerations

- Reuse existing database connections and DAO patterns.
- Avoid unnecessary repeated queries.
- Retrieve only the information needed for the dashboard.
- Use appropriate database indexes where justified by query patterns.
- Handle database failures without displaying misleading statistics.

## 7. Security Considerations

- Restrict administrative statistics to authorized users.
- Avoid exposing individual student information unnecessarily.
- Do not expose database credentials or internal errors.
- Validate any user-supplied filters used for statistics.

## 8. Development Tasks

- Review the current dashboard metrics.
- Document the definition of each metric.
- Inspect the corresponding SQL queries.
- Identify missing or inaccurate calculations.
- Implement necessary corrections.
- Verify the displayed values against database records.

## 9. Expected Deliverables

- Placement statistics requirements.
- Definitions of the dashboard metrics.
- Review of existing calculations.
- Testing checklist for statistics accuracy.

## 10. Completion Criteria

The planning work is complete when the required metrics and their calculation rules are documented.

Actual implementation and verification must be completed separately.

## Commit Message

docs: add admin placement statistics plan for week 7
