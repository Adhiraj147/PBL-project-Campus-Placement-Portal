# Audit Ledger & Interview Persistence Architecture
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 8 Technical Specification

---

## 1. Compliance Audit Trail (`application_status_history`)

In university placement operations, dispute resolution and accreditation compliance require an immutable audit trail of every status modification. When a recruiter changes a candidate's status:
- Who initiated the change (`changed_by` -> `users.user_id`)?
- What was the previous vs new status (`status`)?
- Why was the decision made (`remarks`)?
- Exactly when did the event occur (`changed_at`)?

### Atomic Dual-Write Transaction Pattern:
```java
conn.setAutoCommit(false);

// Statement 1: Mutate current application state
stmt1.setString(1, status);
stmt1.setInt(2, applicationId);
stmt1.executeUpdate();

// Statement 2: Append immutable audit record
stmt2.setInt(1, applicationId);
stmt2.setString(2, status);
stmt2.setString(3, remarks);
stmt2.setInt(4, changedByUserId);
stmt2.executeUpdate();

conn.commit();
```

### Fault-Tolerance Guarantee:
If the database server encounters a disk fault or network glitch during statement 2, `conn.rollback()` restores the original application state. The system never records a status change without an accompanying audit entry.

---

## 2. Interview Coordination Subsystem (`interviews`)

For shortlisted candidates advancing to interview rounds, interview coordination requires scheduling dates, start times, evaluation types, and remote video meeting links:

```sql
CREATE TABLE IF NOT EXISTS interviews (
    interview_id INT AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    interview_date DATE NOT NULL,
    interview_time TIME NOT NULL,
    type ENUM('TECHNICAL', 'HR', 'ONLINE_TEST') NOT NULL,
    location_link VARCHAR(255),
    instructions TEXT,
    FOREIGN KEY (application_id) REFERENCES applications(application_id) ON DELETE CASCADE
);
```

By linking `interviews` to `application_id`, candidate records, recruiter details, and interview metadata can be retrieved with zero denormalized redundant fields.
