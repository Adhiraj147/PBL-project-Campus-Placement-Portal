# Admin Bug Tracking Plan — Week 10

## 1. Objective

The objective of this week is to establish a structured process for identifying, recording, prioritizing, and verifying defects in the Admin Module of the Campus Placement and Internship Portal.

## 2. Scope

The review may cover the following implemented features:

- Admin authentication and authorization
- Admin dashboard
- Student management
- Company management and verification
- Job and internship moderation
- Application monitoring
- Placement statistics
- Reports and analytics

Only features present in the application should be tested as implemented.

## 3. Bug Reporting Process

For each issue:

1. Reproduce the issue using clear steps.
2. Record the affected page or component.
3. Describe the expected behavior.
4. Describe the actual behavior.
5. Record the severity and impact.
6. Identify the likely component involved, if known.
7. Apply a fix only after the cause has been investigated.
8. Repeat the original test after the fix.
9. Run related regression tests.
10. Record the final result.

## 4. Severity Classification

### Critical

An issue that prevents a core application function or creates a serious security or data-integrity risk.

### High

An issue that makes an important administrative feature unusable or produces significantly incorrect results.

### Medium

An issue that affects part of a feature but has a reasonable workaround.

### Low

A minor display, wording, or usability issue that does not significantly affect functionality.

Severity should be assigned based on the observed impact, not assumptions.

## 5. Potential Areas for Review

- Incorrect servlet mappings
- Broken navigation links
- Invalid form submissions
- Incorrect database query results
- Inconsistent status values
- Missing authorization checks
- Incorrect dashboard or report totals
- Poor handling of empty results
- Unexpected session behavior
- Error messages that expose technical details

These are areas to investigate, not confirmed defects.

## 6. Bug Record Template

For each confirmed issue, record:

- Bug ID
- Short title
- Affected feature
- Steps to reproduce
- Expected result
- Actual result
- Severity
- Fix or resolution
- Retest result

## 7. Verification Rules

- Do not mark an issue as fixed until it has been retested.
- Do not record a suspected issue as a confirmed bug without reproducing it.
- Record unresolved issues honestly.
- Avoid making unrelated code changes while fixing a defect.
- Review the impact of changes on dependent features.

## 8. Expected Outcome

A consistent method for tracking and verifying defects in the Admin Module.

## 9. Current Status

Status: Bug tracking process documented. No bugs are claimed as discovered or fixed by this document alone.

## Commit Message

`docs: add admin bug tracking plan for week 10`
