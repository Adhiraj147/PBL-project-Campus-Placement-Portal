# Test Case Specifications Matrix (TC-01 through TC-25)
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 11 Technical Specification

---

## 1. Specification Matrix

| Test ID | Module / Component | Objective | Input Data / Precondition | Expected Result | Actual Result | Status |
| :---: | :--- | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | JDBC Infrastructure | Verify valid MySQL connection | Valid JDBC URL, credentials | Active connection returned, not closed | Active connection verified | **PASS** |
| **TC-02** | User Identity | Verify user record insertion | User entity with BCrypt hash | Positive auto-generated `user_id` | Auto-generated PK returned | **PASS** |
| **TC-03** | User Identity | Verify lookup by email | Existing admin email | Non-null `User` entity, `role=ADMIN` | Successfully resolved | **PASS** |
| **TC-04** | User Transactions | Verify atomic student registration | New user + student metadata | Both `users` and `students` committed | Atomically committed | **PASS** |
| **TC-05** | User Transactions | Verify atomic recruiter registration | New user + company name | Both `users` and `companies` committed | Atomically committed | **PASS** |
| **TC-06** | Database Integrity | Verify duplicate email rejection | Existing email inserted again | `SQLException` thrown, rollback occurs | Duplicate rejected | **PASS** |
| **TC-07** | Student Portfolio | Verify composite student hydration | Existing `user_id` | Populated education, projects, skills | Clean hydration without bloat | **PASS** |
| **TC-08** | Student Portfolio | Verify basic profile update | Updated name, GPA, resume URL | `profile_completed=true` updated | Profile updated | **PASS** |
| **TC-09** | Student Portfolio | Verify academic record creation | Degree, institution, passing year | Record saved in `education` table | Record persisted | **PASS** |
| **TC-10** | Student Portfolio | Verify project showcase persistence | Project title, URL, description | Record saved in `projects` table | Record persisted | **PASS** |
| **TC-11** | Student Portfolio | Verify idempotent skill tagging | Skill tag associated with student | `INSERT IGNORE` into `student_skills` | Junction record created | **PASS** |
| **TC-12** | Recruiter Company | Verify corporate profile update | Company name, industry, website | Attributes updated in `companies` | Attributes updated | **PASS** |
| **TC-13** | Opportunity Drive | Verify drive creation with cutoffs | Min CGPA, eligible branches, deadline | Drive inserted with `status=OPEN` | Successfully inserted | **PASS** |
| **TC-14** | Opportunity Drive | Verify drive lookup with company join | Specific `opp_id` | Joined `company_name` present | Successfully joined | **PASS** |
| **TC-15** | Opportunity Drive | Verify open drives listing | `status='OPEN'` | Only active drives sorted by date | Chronologically returned | **PASS** |
| **TC-16** | Search Engine | Verify multi-parameter search | Keyword "Java", Type "JOB" | Parameterized matching records | Matching results returned | **PASS** |
| **TC-17** | Application Pipeline| Verify student application submit | Valid student and opportunity | Application saved with `status=APPLIED` | Successfully applied | **PASS** |
| **TC-18** | Application Pipeline| Verify duplicate application rejection| Re-applying to same opportunity | Returns `false` or throws constraint err | Rejected duplicate | **PASS** |
| **TC-19** | Application Pipeline| Verify candidate application listing | Target `student_id` | Historical submissions with company info | Full history returned | **PASS** |
| **TC-20** | Application Pipeline| Verify recruiter applicant review | Target `opp_id` | Applicants list with GPA and resumes | Applicants hydrated | **PASS** |
| **TC-21** | Audit Subsystem | Verify status update and audit log | Status transition to SHORTLISTED | Atomic update in `applications` & `history` | Both written atomically | **PASS** |
| **TC-22** | TPO Analytics | Verify placement KPI aggregation | Open connection to DB | Returns 6 discrete counts in one pass | Map with KPIs returned | **PASS** |
| **TC-23** | Eligibility Engine | Verify 4-tier qualification pass | Student with 8.5 GPA, CSE, batch 2025 | `isEligible() == true` | Eligible to apply | **PASS** |
| **TC-24** | Eligibility Engine | Verify CGPA cutoff disqualification | Student with 6.2 GPA vs 7.0 cutoff | `isEligible() == false`, reason: CGPA | Ineligible: CGPA error | **PASS** |
| **TC-25** | Eligibility Engine | Verify discipline disqualification | Mech student vs CSE/IT cutoff | `isEligible() == false`, reason: branch | Ineligible: branch error | **PASS** |
