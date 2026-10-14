# Week 2 — Admin Dashboard Statistics Review

**Contributor:** Krishna  
**Project:** Campus Placement and Internship Portal  
**Module:** Admin & Analytics

## 1. Introduction

Dashboard statistics help administrators understand the current state of a campus placement portal.

This document defines a review approach for the statistics displayed on the administrator dashboard.

## 2. Statistics to Review

### 2.1 Total Students

This statistic represents the number of student records stored in the database.

The displayed value should be compared with the corresponding database count.

### 2.2 Total Companies

This statistic represents the number of company records stored in the database.

The review should determine whether all company records or only specific company statuses are included.

### 2.3 Total Jobs

This statistic represents the number of job opportunities stored in the database.

The review should determine whether closed or inactive job postings are included in the total.

### 2.4 Total Internships

This statistic represents the number of internship opportunities stored in the database.

The counting rules should be consistent with the intended dashboard definition.

### 2.5 Total Applications

This statistic represents the number of application records stored in the database.

The displayed value should be compared with the actual application count.

### 2.6 Selected Applications

This statistic represents applications marked with the selected status, where that status is supported by the existing application workflow.

The implementation should use the actual status values defined in the project.

## 3. Verification Procedure

For each statistic:

1. Identify the DAO method responsible for retrieving the value.
2. Review the associated SQL query.
3. Identify the relevant database table and counting conditions.
4. Execute the corresponding database query.
5. Compare the database result with the dashboard value.
6. Record any discrepancy.
7. Correct the implementation only after identifying the cause.

## 4. Data Accuracy Considerations

The following issues should be checked:

- Duplicate counting caused by joins.
- Incorrect table or column references.
- Incorrect application status values.
- Exclusion of valid records.
- Inclusion of records that should not be counted.
- Errors caused by unavailable database connections.

## 5. Suggested Results Table

| Statistic | Expected Value | Displayed Value | Result |
|---|---|---|---|
| Total Students | To be verified | To be verified | Not tested |
| Total Companies | To be verified | To be verified | Not tested |
| Total Jobs | To be verified | To be verified | Not tested |
| Total Internships | To be verified | To be verified | Not tested |
| Total Applications | To be verified | To be verified | Not tested |
| Selected Applications | To be verified | To be verified | Not tested |

Update this table only after performing the actual database and dashboard checks.

## 6. Error Handling

The dashboard should handle database errors without presenting incorrect values as verified results.

The application should record useful diagnostic information while avoiding exposure of credentials or sensitive database details.

## 7. Expected Outcome

The review should establish whether dashboard statistics accurately represent the underlying database records.

Any identified discrepancies should be recorded for correction and retesting.

## 8. Conclusion

Reliable dashboard statistics are important for administrative decisions.

A systematic comparison between database results and dashboard values will help improve the accuracy and reliability of the Admin & Analytics module.

Commit message -docs: document admin dashboard statistics review
