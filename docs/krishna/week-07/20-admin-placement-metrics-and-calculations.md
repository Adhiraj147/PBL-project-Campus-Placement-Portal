# Week 7 — Admin Placement Metrics and Calculations

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics  
**Week:** 7

## 1. Introduction

Placement metrics summarize information stored in the Campus Placement and Internship Portal.

This document defines possible calculations for the administrator dashboard. The final calculations must match the actual database schema and project requirements.

## 2. Total Students

**Purpose:** Measure the number of student records.

**Calculation:**

Total Students = Count of eligible student records.

The definition of eligible records must be established from the existing database and business rules.

## 3. Total Companies

**Purpose:** Measure the number of company records.

**Calculation:**

Total Companies = Count of eligible company records.

If the dashboard distinguishes verified and unverified companies, each category should have a clearly defined counting rule.

## 4. Total Job Postings

**Purpose:** Measure the number of job opportunities.

**Calculation:**

Total Jobs = Count of job records that meet the chosen counting criteria.

The implementation should specify whether closed or inactive postings are included.

## 5. Total Internship Postings

**Purpose:** Measure the number of internship opportunities.

**Calculation:**

Total Internships = Count of internship records that meet the chosen counting criteria.

The counting rule should be consistent with the intended meaning of the dashboard metric.

## 6. Total Applications

**Purpose:** Measure application activity in the portal.

**Calculation:**

Total Applications = Count of eligible application records.

The query should count application records rather than counting joined student or opportunity rows.

## 7. Selected Applications

**Purpose:** Measure applications recorded as selected.

**Calculation:**

Selected Applications = Count of application records whose status matches the existing selected-status value.

The exact status value must be obtained from the project implementation.

## 8. Application Status Distribution

Where supported, the dashboard may summarize applications by status.

For example, it may display the number of applications in each supported status category.

The categories and values must be taken from the existing application workflow.

## 9. Example Calculations

The following values are hypothetical and are provided only to explain the calculations.

| Metric | Example Value |
|---|---:|
| Total Students | 200 |
| Total Companies | 20 |
| Total Jobs | 35 |
| Total Internships | 15 |
| Total Applications | 300 |
| Selected Applications | 45 |

These values are not actual results from the project database.

## 10. Data Validation

- Compare every displayed metric with the corresponding database query.
- Confirm the relevant table and counting conditions.
- Check for duplicate rows caused by joins.
- Verify that status comparisons use valid values.
- Test behavior when the database contains no records.
- Handle database errors appropriately.

## 11. Implementation Considerations

Use the existing DAO layer for database operations.

Avoid hardcoding statistics in the dashboard.

Use parameterized queries where user-supplied parameters are involved.

Document the counting criteria so that dashboard values can be interpreted consistently.

## 12. Conclusion

Clearly defined placement metrics improve the usefulness of the administrator dashboard.

Each metric should be calculated from actual database records and verified before being presented as a confirmed project statistic.

## Commit Message

docs: document admin placement metrics and calculations
