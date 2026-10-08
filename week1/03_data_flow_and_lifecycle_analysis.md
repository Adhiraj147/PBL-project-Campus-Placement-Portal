# Data Flow and Lifecycle Analysis
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Data Flow Overview

To ensure high data cohesion between the front-end presentation views (JSP), the HTTP controllers (Java Servlets), and the persistence tier (JDBC / MySQL), a systematic Data Flow Diagram (DFD) analysis was conducted during Week 1.

The system processes data across three distinct lifecycles:
1. **User Identity & Onboarding Lifecycle**
2. **Opportunity Authoring & Publishing Lifecycle**
3. **Application Screening & Interview Dispatch Lifecycle**

---

## 2. Level 0: Context Data Flow Diagram

```mermaid
flowchart TD
    S([Student Candidate]) <-->|Registration, Profile, Applications| SYS[Campus Placement and Internship Portal]
    R([Corporate Recruiter]) <-->|Company Profile, Job Postings, Status Updates| SYS
    A([TPO Administrator]) <-->|Governance, Approvals, Global Statistics| SYS
    SYS <-->|ACID Transactions & Persistence| DB[(MySQL 8.0 Database)]
```

---

## 3. Level 1: Core Subsystem Data Flows

### 3.1 Student Registration & Profile Completion Workflow
```mermaid
sequenceDiagram
    autonumber
    actor Student
    participant Servlet as RegisterServlet
    participant UserDAO as UserDAOImpl
    participant StudentDAO as StudentDAOImpl
    participant DB as MySQL Database

    Student->>Servlet: Submits registration (email, password, role='STUDENT', name, rollNo)
    Servlet->>UserDAO: registerStudentWithTransaction(user, firstName, lastName, rollNo)
    Note over UserDAO,DB: Transaction Begins (setAutoCommit=false)
    UserDAO->>DB: INSERT INTO users (email, password_hash, role='STUDENT')
    DB-->>UserDAO: Returns generated user_id
    UserDAO->>DB: INSERT INTO students (user_id, first_name, last_name, roll_no)
    DB-->>UserDAO: Record persisted
    Note over UserDAO,DB: Transaction Committed (commit)
    UserDAO-->>Servlet: Returns new user_id
    Servlet-->>Student: Redirects to Login with Success Message
```

### 3.2 Automated Eligibility & Application Submission Workflow
```mermaid
sequenceDiagram
    autonumber
    actor Student
    participant Servlet as StudentOpportunityServlet
    participant Service as EligibilityService
    participant AppDAO as ApplicationDAOImpl
    participant DB as MySQL Database

    Student->>Servlet: Clicks "Apply" for Opportunity (opp_id)
    Servlet->>DB: Fetch Student profile by user_id
    Servlet->>DB: Fetch Opportunity details by opp_id
    Servlet->>Service: checkEligibility(student, opportunity)
    alt Incomplete Profile or Below CGPA or Branch Mismatch
        Service-->>Servlet: Returns EligibilityResult(false, reason)
        Servlet-->>Student: Displays error notice (Reason: Ineligible)
    else Meets All Criteria
        Service-->>Servlet: Returns EligibilityResult(true, "Eligible")
        Servlet->>AppDAO: hasApplied(student_id, opp_id)
        alt Already Applied
            AppDAO-->>Servlet: true
            Servlet-->>Student: Warning: Duplicate application rejected
        else Fresh Application
            AppDAO-->>Servlet: false
            AppDAO->>DB: INSERT INTO applications (student_id, opp_id, status='APPLIED')
            AppDAO->>DB: INSERT INTO application_status_history (application_id, status='APPLIED')
            Servlet-->>Student: Displays confirmation toast: "Application Submitted Successfully"
        end
    end
```

### 3.3 Recruiter Screening & Status Progression Workflow
```mermaid
stateDiagram-v2
    [*] --> APPLIED : Candidate Applies
    APPLIED --> UNDER_REVIEW : Recruiter Opens Candidate Profile
    UNDER_REVIEW --> SHORTLISTED : Evaluator Accepts Profile
    UNDER_REVIEW --> REJECTED : Profile Incompatible
    SHORTLISTED --> INTERVIEW_SCHEDULED : Date/Time Slot Fixed
    INTERVIEW_SCHEDULED --> SELECTED : Cleared All Evaluation Rounds
    INTERVIEW_SCHEDULED --> REJECTED : Evaluation Performance Low
    SELECTED --> [*]
    REJECTED --> [*]
```

---

## 4. Concurrency Control and Data Integrity

1. **Duplicate Prevention**: The combination of `student_id` and `opp_id` in the `applications` table is strictly bound by a `UNIQUE` composite key. Any race condition where two rapid clicks occur is absorbed gracefully with an `SQLIntegrityConstraintViolationException` caught in `ApplicationDAOImpl`.
2. **Cascading Deletions**: Deleting a user account cascades to remove associated student/company records, their application records, portfolio details, and notifications, ensuring no orphan rows clutter the database.
3. **Auditing**: Every status change triggered by a recruiter or admin inserts a record into `application_status_history` logging the previous state, new state, user ID of the updater, and optional evaluation remarks.
