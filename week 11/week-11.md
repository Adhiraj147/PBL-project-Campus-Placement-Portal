# Week 11 — Applicant Status Management

## Project
Campus Placement and Internship Portal

## Module
Recruiter and Company Portal

## Branch
feature/saurabh

## Objective
Allow recruiters to manage and filter applicant statuses
for their company's job and internship opportunities.

## Features Implemented
- Added applicant status filtering.
- Added an Under Review status option.
- Added Shortlisted status management.
- Added Interview Scheduled status management.
- Added Selected status management.
- Added Rejected status management.
- Added optional remarks when changing status.
- Saved status changes in the database.
- Recorded status changes in application_status_history.
- Added status-specific visual badges.
- Validated the allowed status values.
- Verified company ownership before changing applicant status.

## Files Changed
- src/main/java/com/placement/dao/ApplicationDAO.java
- src/main/java/com/placement/dao/ApplicationDAOImpl.java
- src/main/java/com/placement/controller/RecruiterApplicationServlet.java
- src/main/webapp/jsp/recruiter/applicants.jsp

## Supported Statuses
- APPLIED
- UNDER_REVIEW
- SHORTLISTED
- INTERVIEW_SCHEDULED
- SELECTED
- REJECTED

## Testing Checklist
- [ ] All applicants can be displayed.
- [ ] The status filter displays the correct applicants.
- [ ] An applicant can be moved to Under Review.
- [ ] An applicant can be shortlisted.
- [ ] An applicant can be marked Interview Scheduled.
- [ ] An applicant can be marked Selected.
- [ ] An applicant can be rejected.
- [ ] Optional remarks are saved.
- [ ] Status history is recorded.
- [ ] Invalid status values are rejected.
- [ ] Recruiters cannot update another company's applicants.
- [ ] Existing applicant and resume listing still works.

## Commit Message
feat: implement applicant status management