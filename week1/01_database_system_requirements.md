# System Requirements & Database Planning Specification
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal (B.Tech Advanced Java PBL)

---

## 1. Executive Summary

In a modern university placement ecosystem, disparate manual spreadsheets, paper resumes, and ad-hoc email communications result in data fragmentation, delayed application cycles, and lack of transparency. The **Campus Placement and Internship Portal** addresses these challenges through a centralized, web-based platform built on pure Java Enterprise principles (Servlets, JSP, JDBC, MySQL, MVC).

As the lead for **Module 5 (Database Architecture, Integration Layer & System Testing)**, my responsibility is to design, implement, and maintain the underlying relational data storage, transactional integrity mechanisms, Java Data Access Object (DAO) layer, cross-module business service integration, and system-wide verification suites.

This document outlines the foundational system requirements, concurrency constraints, data volume projections, and ACID guarantees established during **Week 1** of project development.

---

## 2. Stakeholder Profiles & Functional Data Requirements

The database schema must cater to three primary user personas with distinct access patterns, operational requirements, and data lifecycles:

### 2.1 Student Persona (Candidate)
- **Profile Management**: Storage of biographical details (name, roll number, department/branch, batch/graduation year, CGPA, phone, resume hyperlink) and extended qualification vectors (secondary education, semester percentage history, verified skills, academic projects, professional certifications, prior internship experiences).
- **Opportunity Discovery**: Low-latency querying of open campus drives, filtered by opportunity type (`JOB` vs. `INTERNSHIP`), work mode (`ONSITE`, `REMOTE`, `HYBRID`), compensation, and application deadlines.
- **Automated Eligibility Verification**: Pre-application verification matching student CGPA against company threshold (`min_cgpa`), department eligibility lists (`eligible_branches`), and profile completion status (100% threshold).
- **Application Submission & Tracking**: Unique student-to-opportunity binding, status history auditing (`APPLIED`, `UNDER_REVIEW`, `SHORTLISTED`, `INTERVIEW_SCHEDULED`, `SELECTED`, `REJECTED`), and interview schedule notifications.

### 2.2 Recruiter Persona (Corporate Partner)
- **Company Profile Representation**: Corporate details, industry sector, registered headquarters, corporate contact person, and official website.
- **Job & Internship Posting**: Authoring opportunities with strict qualification constraints (minimum CGPA, allowed graduation years, eligible branch csv lists, required skill sets, deadline dates).
- **Candidate Pipeline Processing**: Retrieving applicants for posted opportunities, evaluating qualifications, advancing candidate states, adding internal evaluator remarks, and scheduling technical/HR interview slots.

### 2.3 Administrator Persona (Training & Placement Cell / TPO)
- **System Governance**: Platform moderation, user lifecycle management (`ACTIVE`, `PENDING`, `INACTIVE`), account verification.
- **Global Placement Analytics**: High-level real-time KPI metrics including total registered students, active recruiters, aggregate opportunities posted, total applications submitted, interview schedules, and final selections.

---

## 3. Non-Functional Data Requirements

| Requirement Category | Metric / Specification | Technical Justification |
| :--- | :--- | :--- |
| **Data Integrity & Consistency** | Strict 3NF Relational Schema with Foreign Key Constraints | Prevents orphan records during account deactivation (`ON DELETE CASCADE` / `SET NULL`). |
| **Concurrency & Thread Safety** | ACID compliance via InnoDB engine in MySQL | Concurrent applications from thousands of students during campus drive launch without race conditions. |
| **Response Latency** | Query execution time `< 50ms` for core listings | Targeted B-Tree indexing on `user_id`, `email`, `student_id`, `opp_id`, and `status`. |
| **Security & Privacy** | One-way BCrypt hashing (`password_hash VARCHAR(255)`) | Sensitive credentials never stored in plaintext; SQL injection prevented via parameterized `PreparedStatement`. |
| **Extensibility & Modularity** | Decoupled DAO interfaces (`UserDAO`, `StudentDAO`, etc.) | Enables switching persistence backends or connection pooling without rewriting servlet controllers. |

---

## 4. ACID Transaction Guarantees in Application Workflows

1. **Atomicity**: During multi-step operations (e.g., student registration requiring simultaneous insertion into `users` and `students` tables), JDBC transactions use `conn.setAutoCommit(false)` and `conn.commit()`. If either insert fails, `conn.rollback()` restores the initial state.
2. **Consistency**: Database schema constraints (`UNIQUE(email)`, `UNIQUE(roll_no)`, `UNIQUE(student_id, opp_id)`) prevent duplicate user accounts and duplicate job submissions.
3. **Isolation**: Read Committed / Repeatable Read isolation levels guarantee that recruiter candidate shortlisting does not conflict with simultaneous student status checks.
4. **Durability**: Persistent write-ahead logging (WAL) of the MySQL InnoDB storage engine ensures confirmed applications remain durable across server crashes.

---

## 5. Technology Stack Selection Rationale

- **Database Engine**: **MySQL 8.0+ (InnoDB)** — Chosen for enterprise relational reliability, ANSI SQL adherence, and foreign key referential integrity.
- **Driver**: **MySQL Connector/J (`mysql-connector-java:8.0.33`)** — Official Type 4 pure Java JDBC driver providing optimal network socket performance with zero native client dependencies.
- **Build & Dependency Automation**: **Apache Maven** — Standardized dependency resolution and unified compilation lifecycle.
- **Application Server**: **Apache Tomcat 9.0 (Servlet 4.0 / JSP 2.3)** — Enterprise container for hosting Java Servlets and JSP views.
