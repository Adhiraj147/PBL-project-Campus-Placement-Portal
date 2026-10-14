# Admin Report Data and Metrics

## 1. Purpose

This document defines the proposed data points and calculation rules for reports in the Campus Placement and Internship Portal.

The metrics described below are specifications. They should be used only after the corresponding fields and records have been confirmed in the project's database.

## 2. Student Metrics

Possible metrics include:

- Total registered students
- Students with complete profiles
- Students who have applied for at least one opportunity
- Students with a recorded placement status

### Calculation Rules

- Count distinct student records rather than application records.
- Count a student as placed only when a valid placement record or supported placement status exists.
- Do not assume that every applicant is eligible or placed.

## 3. Company Metrics

Possible metrics include:

- Total registered companies
- Verified companies
- Pending company verifications
- Rejected or inactive companies, if these statuses exist

### Calculation Rules

Each company should be counted once per applicable category. The report should use the actual verification status stored by the application.

## 4. Job and Internship Metrics

Possible metrics include:

- Total job postings
- Total internship postings
- Pending postings
- Approved postings
- Rejected postings
- Closed or expired postings, if supported

### Calculation Rules

- Use the stored posting type and moderation status.
- Avoid counting the same posting more than once.
- Apply date filters consistently when dates are available.

## 5. Application Metrics

Possible metrics include:

- Total applications
- Applications pending review
- Applications shortlisted
- Applications rejected
- Applications selected

The final status categories must match the statuses supported by the existing application.

### Calculation Rules

- Count application records, not distinct students, when reporting application totals.
- Use distinct students when the metric specifically measures students.
- Verify that status categories are mutually exclusive before adding them together.
- Do not assume that an unreviewed application is rejected.

## 6. Placement Metrics

A placement rate may be calculated as:

Placement Rate = (Number of eligible placed students / Total eligible students) × 100

Before using this formula, the project must define eligibility and confirm that the required records are available.

If eligible-student data is unavailable, display the placement count without presenting an unverified percentage.

## 7. Date Filters

Where the database contains reliable date fields, reports may support:

- Start date
- End date
- Academic year
- Posting date range
- Application date range

The start date must not be later than the end date. The application should clearly indicate which date field a filter uses.

## 8. Data Quality Checks

- Check for missing required fields.
- Check for duplicate records where uniqueness is expected.
- Confirm that report totals match the underlying records.
- Handle empty datasets.
- Validate date ranges.
- Avoid dividing by zero when calculating percentages.
- Document any metric that cannot be calculated from the existing schema.

## 9. Verification Method

For each implemented metric:

1. Identify its source table and field.
2. Record the expected result from a known test dataset.
3. Run the report.
4. Compare the displayed result with the expected result.
5. Investigate discrepancies before marking the metric as verified.

## 10. Current Status

Status: Metric definitions documented. Database mappings, calculations, and outputs require verification against the actual project implementation.

## Commit Message

`docs: document admin report data and metrics`
