# Candidate Application Workflow & Audit Ledger Tracking
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Candidate Application State Machine

The recruitment journey progresses through a deterministic finite-state automaton:

```mermaid
stateDiagram-v2
    [*] --> APPLIED : Candidate submits via Portal
    APPLIED --> UNDER_REVIEW : Recruiter screens qualifications
    UNDER_REVIEW --> SHORTLISTED : Clears screening benchmark
    UNDER_REVIEW --> REJECTED : Does not meet criteria
    SHORTLISTED --> INTERVIEW_SCHEDULED : TPO/Recruiter fixes slot
    INTERVIEW_SCHEDULED --> SELECTED : Clears all evaluation rounds
    INTERVIEW_SCHEDULED --> REJECTED : Inadequate round performance
    SELECTED --> [*] : Offer Letter Generated
    REJECTED --> [*] : Archival
```

---

## 2. Atomic Audit Tracking in `ApplicationDAOImpl`

When status transitions occur, both the `applications` row and an audit ledger entry in `application_status_history` are written in a single ACID transaction (`conn.commit()`), ensuring complete auditability.
