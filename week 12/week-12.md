# Week 12 — Interview Scheduling and Final Workflow

## Project
Campus Placement and Internship Portal

## Module
Recruiter and Company Portal

## Branch
feature/saurabh

## Objective
Allow recruiters to schedule interviews for eligible applicants.

## Features Implemented
- Created InterviewDAO.
- Created InterviewDAOImpl.
- Created InterviewSchedulingServlet.
- Added interview date and time selection.
- Added Technical, HR, and Online Test interview types.
- Added meeting link or location.
- Added interview instructions.
- Validated interview dates and times.
- Restricted scheduling to authenticated recruiters.
- Checked company ownership of the opportunity.
- Updated application status to INTERVIEW_SCHEDULED.
- Recorded the status change in application_status_history.
- Added an interview scheduling form to applicant management.

## Files Added
- src/main/java/com/placement/dao/InterviewDAO.java
- src/main/java/com/placement/dao/InterviewDAOImpl.java
- src/main/java/com/placement/controller/InterviewSchedulingServlet.java
- docs/weekly-submissions/week-12.md

## Files Updated
- src/main/webapp/jsp/recruiter/applicants.jsp

## Database Table
interviews

## Testing Checklist
- [ ] Recruiter can open applicant management.
- [ ] Shortlisted applicants can be scheduled for interviews.
- [ ] Interview date and time are saved.
- [ ] Interview type is saved.
- [ ] Meeting link or location is saved.
- [ ] Instructions are saved.
- [ ] Application status becomes INTERVIEW_SCHEDULED.
- [ ] Invalid interview dates are rejected.
- [ ] Invalid interview types are rejected.
- [ ] Recruiters cannot schedule interviews for another company's applicants.
- [ ] Student interview page displays scheduled interviews.
- [ ] Existing recruiter and student workflows still work.

## Commit Message
feat: add interview scheduling