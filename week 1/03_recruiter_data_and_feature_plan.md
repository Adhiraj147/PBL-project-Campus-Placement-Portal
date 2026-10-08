# Week 1 – Recruiter Data and Feature Development Plan

## Student

Saurabh Gupta

## Module

Recruiter & Company Portal

## GitHub Branch

feature/saurabh

---

# 1. Purpose

The purpose of this document is to identify the major information requirements and functional features of the Recruiter & Company Portal.

The identified data and features will be used as the foundation for subsequent implementation.

---

# 2. Recruiter Information

The recruiter section requires information necessary for creating and managing recruiter accounts.

### Planned Information

- Recruiter name
- Email address
- Password
- Contact information
- Associated company
- Account/role information

### Main Operations

- Recruiter registration
- Recruiter authentication
- Recruiter dashboard access
- Logout

---

# 3. Company Information

The company profile section stores information about the organization represented by the recruiter.

### Planned Information

- Company name
- Company description
- Website
- Industry
- Location
- Contact information
- Recruiter/company association

### Main Operations

- View company profile
- Edit company profile
- Save company information

---

# 4. Job Posting Information

The job module is responsible for employment opportunities created by recruiters.

### Planned Information

- Job title
- Job description
- Required skills
- Eligibility information
- Location
- Salary or compensation information
- Number of vacancies
- Application deadline
- Posting status

### Main Operations

- Create job
- View job
- Edit job
- Delete job
- Close job

---

# 5. Internship Posting Information

The internship module is responsible for internship opportunities created by recruiters.

### Planned Information

- Internship title
- Internship description
- Required skills
- Eligibility information
- Duration
- Stipend
- Location
- Application deadline
- Posting status

### Main Operations

- Create internship
- View internship
- Edit internship
- Delete internship
- Close internship

---

# 6. Application Information

Applications connect students with jobs and internships.

### Recruiter-Side Operations

- View received applications
- View applicant details
- View applicant resume where available
- Review applications
- Update application status
- Shortlist applicants
- Reject applicants

---

# 7. Applicant Status

Applicant status is required to represent the current stage of a student's application.

The planned recruitment process is:

Applied
   |
   v
Under Review
   |
   +----------------+
   |                |
   v                v
Shortlisted       Rejected
   |
   v
Interview Scheduled
   |
   v
Further Recruitment Decision

Status management allows the recruiter to track applicants throughout the recruitment process.

---

# 8. Interview Information

The interview workflow is used after an applicant has been shortlisted.

### Planned Information

- Applicant
- Job or internship opportunity
- Interview date
- Interview time
- Interview mode
- Interview link/location
- Interview instructions
- Interview status

### Main Operations

- Schedule interview
- View scheduled interview
- Manage interview information

---

# 9. Feature Development Plan

| Feature Area | Planned Work |
|---|---|
| Recruiter Registration | Registration and validation |
| Recruiter Dashboard | Central recruiter interface |
| Company Profile | View and update company information |
| Job Posting | Create and manage jobs |
| Internship Posting | Create and manage internships |
| Applicant Management | View applications and applicant details |
| Shortlisting | Shortlist suitable applicants |
| Rejection | Reject unsuitable applicants |
| Status Management | Track application progress |
| Interview Scheduling | Schedule and manage interviews |

---

# 10. Development Sequence

The features will be developed progressively.

### Foundation

- Recruiter registration
- Recruiter dashboard
- Company profile

### Core Recruitment Features

- Job posting
- Job management
- Internship posting
- Internship management
- Applicant management

### Advanced Recruitment Features

- Shortlisting
- Rejection
- Applicant status management
- Interview scheduling

### Finalization

- Testing
- Bug fixing
- Integration
- Documentation

---

# 11. Week 1 Outcome

The recruiter-side data requirements and feature development sequence have been identified.

This plan provides a clear roadmap for implementing the Recruiter & Company Portal while keeping the module connected with the other components of the Campus Placement and Internship Portal.

---

# 12. Conclusion

Week 1 established the functional and data foundation required for the Recruiter & Company Portal. The planned information, operations and development sequence will be used for the implementation work in the following weeks.