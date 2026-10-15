```markdown
# Week 10 — Shortlist and Reject Applicants

## Project
Campus Placement and Internship Portal

## Module
Recruiter and Company Portal

## Branch
feature/saurabh

## Objective
Allow recruiters to shortlist or reject applicants for their
company's job and internship postings.

## Features Implemented
- Added Shortlist and Reject buttons.
- Validated application and opportunity IDs.
- Restricted status updates to recruiter accounts.
- Verified that the opportunity belongs to the recruiter's company.
- Updated application status in the database.
- Recorded status changes in application_status_history.
- Added confirmation prompts before status changes.
- Displayed a success message after an update.

## Files Changed
- src/main/java/com/placement/dao/ApplicationDAO.java
- src/main/java/com/placement/dao/ApplicationDAOImpl.java
- src/main/java/com/placement/controller/RecruiterApplicationServlet.java
- src/main/webapp/jsp/recruiter/applicants.jsp

## Status Values
- SHORTLISTED
- REJECTED

## Testing Checklist
- [ ] Recruiter can view applicants.
- [ ] Shortlist changes the status to SHORTLISTED.
- [ ] Reject changes the status to REJECTED.
- [ ] Status changes are saved in the history table.
- [ ] Invalid status values are rejected.
- [ ] Invalid IDs are rejected.
- [ ] Recruiters cannot update another company's applicants.
- [ ] Non-recruiters cannot update application status.
- [ ] Existing applicant listing still works.

## Commit Message
feat: add shortlist functionality
```
