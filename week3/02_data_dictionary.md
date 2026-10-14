# System Data Dictionary & Column Specifications
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 3 Technical Specification

---

## 1. Overview

This document provides the definitive data dictionary for all 15 relational tables in the **Campus Placement and Internship Portal** (`campus_placement_db`). It details data types, nullability, primary/foreign keys, check constraints, default values, and operational purposes.

---

## 2. Table Specifications

### 2.1 Table: `users`
Central identity store managing authentication and role-based access.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `user_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for the account |
| `email` | `VARCHAR(100)` | NO | `UNI` | *None* | Institutional or company email address |
| `password_hash` | `VARCHAR(255)` | NO | | *None* | BCrypt hashed password string ($2a$10$...) |
| `role` | `ENUM('STUDENT','RECRUITER','ADMIN')` | NO | `IDX` | *None* | System authorization role |
| `status` | `ENUM('ACTIVE','PENDING','INACTIVE')` | NO | | `'ACTIVE'` | Account activation status |
| `created_at` | `TIMESTAMP` | NO | | `CURRENT_TIMESTAMP` | Account registration timestamp |

---

### 2.2 Table: `students`
Profile records for undergraduate and postgraduate candidates.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `student_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Surrogate primary key for student |
| `user_id` | `INT` | NO | `FK, UNI` | *None* | References `users(user_id)` ON DELETE CASCADE |
| `first_name` | `VARCHAR(50)` | NO | | *None* | Candidate first name |
| `last_name` | `VARCHAR(50)` | NO | | *None* | Candidate surname |
| `roll_no` | `VARCHAR(20)` | YES | `UNI` | `NULL` | University enrollment roll number |
| `branch` | `VARCHAR(50)` | YES | `IDX` | `NULL` | Academic department (e.g., Computer Science) |
| `graduation_year`| `INT` | YES | | `NULL` | Expected year of degree completion |
| `cgpa` | `DECIMAL(4,2)` | YES | `IDX` | `NULL` | Cumulative Grade Point Average (0.00 - 10.00) |
| `phone` | `VARCHAR(15)` | YES | | `NULL` | Contact telephone number |
| `resume_url` | `VARCHAR(255)` | YES | | `NULL` | Cloud path to verified PDF resume |
| `profile_completed`| `BOOLEAN` | NO | | `FALSE` | Flag indicating 100% profile completeness |

---

### 2.3 Table: `companies`
Registered corporate recruiting organizations.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `company_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for corporate entity |
| `user_id` | `INT` | NO | `FK, UNI` | *None* | References `users(user_id)` ON DELETE CASCADE |
| `company_name` | `VARCHAR(100)` | NO | `IDX` | *None* | Registered corporate brand name |
| `description` | `TEXT` | YES | | `NULL` | Company overview and industry focus |
| `website` | `VARCHAR(100)` | YES | | `NULL` | Corporate domain URL |
| `industry` | `VARCHAR(50)` | YES | | `NULL` | Industry sector (Fintech, EdTech, SaaS) |
| `location` | `VARCHAR(100)` | YES | | `NULL` | Corporate headquarters or recruitment base |
| `contact_person`| `VARCHAR(100)`| YES | | `NULL` | Designated HR contact name |

---

### 2.4 Table: `admins`
TPO administrative coordinators.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `admin_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for administrator |
| `user_id` | `INT` | NO | `FK, UNI` | *None* | References `users(user_id)` ON DELETE CASCADE |
| `name` | `VARCHAR(100)` | NO | | *None* | Administrative officer full name |
| `employee_id` | `VARCHAR(20)` | YES | `UNI` | `NULL` | University faculty/staff identifier |

---

### 2.5 Table: `opportunities`
Placement drives, full-time jobs, and internships posted by employers.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `opp_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Primary key for placement posting |
| `company_id` | `INT` | NO | `FK, IDX` | *None* | References `companies(company_id)` ON DELETE CASCADE |
| `title` | `VARCHAR(100)` | NO | | *None* | Position job title |
| `type` | `ENUM('JOB','INTERNSHIP')` | NO | | *None* | Employment arrangement |
| `description` | `TEXT` | NO | | *None* | Job description and duties |
| `location` | `VARCHAR(100)` | YES | | `NULL` | Work location |
| `work_mode` | `ENUM('ONSITE','REMOTE','HYBRID')` | NO | | `'ONSITE'` | Work arrangement mode |
| `salary_stipend` | `VARCHAR(50)` | YES | | `NULL` | Compensation package |
| `min_cgpa` | `DECIMAL(4,2)` | NO | | `0.00` | Minimum qualifying CGPA cutoff |
| `eligible_branches`| `VARCHAR(255)`| YES | | `NULL` | Comma-separated eligible disciplines |
| `graduation_year_req`| `INT` | YES | | `NULL` | Mandatory target graduation batch |
| `required_skills`| `VARCHAR(255)` | YES | | `NULL` | Key technical proficiencies |
| `deadline` | `DATE` | YES | `IDX` | `NULL` | Application cutoff date |
| `status` | `ENUM('OPEN','CLOSED')`| NO | `IDX` | `'OPEN'` | Drive submission state |
| `created_at` | `TIMESTAMP` | NO | | `CURRENT_TIMESTAMP` | Publishing timestamp |

---

### 2.6 Table: `applications`
Application submissions by candidates for posted opportunities.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `application_id`| `INT` | NO | `PK` | `AUTO_INCREMENT` | Primary key for application instance |
| `student_id` | `INT` | NO | `FK, IDX, UQ` | *None* | References `students(student_id)` ON DELETE CASCADE |
| `opp_id` | `INT` | NO | `FK, IDX, UQ` | *None* | References `opportunities(opp_id)` ON DELETE CASCADE |
| `status` | `ENUM('APPLIED','UNDER_REVIEW','SHORTLISTED','INTERVIEW_SCHEDULED','SELECTED','REJECTED')` | NO | `IDX` | `'APPLIED'` | Current candidate workflow stage |
| `applied_at` | `TIMESTAMP` | NO | | `CURRENT_TIMESTAMP` | Submission timestamp |

*Note: Unique constraint `uq_student_opportunity (student_id, opp_id)` prevents duplicate applications.*

---

### 2.7 Table: `application_status_history`
Append-only audit trail logging all lifecycle state transitions.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `history_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for history record |
| `application_id`| `INT` | NO | `FK, IDX` | *None* | References `applications(application_id)` ON DELETE CASCADE |
| `status` | `ENUM(...)` | NO | | *None* | Newly transitioned status state |
| `remarks` | `TEXT` | YES | | `NULL` | Recruiter or administrator review notes |
| `changed_by` | `INT` | YES | `FK` | `NULL` | References `users(user_id)` ON DELETE SET NULL |
| `changed_at` | `TIMESTAMP` | NO | | `CURRENT_TIMESTAMP` | State transition timestamp |

---

### 2.8 Table: `interviews`
Evaluation and interview schedules.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `interview_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for interview round |
| `application_id`| `INT` | NO | `FK, IDX` | *None* | References `applications(application_id)` ON DELETE CASCADE |
| `interview_date`| `DATE` | NO | `IDX` | *None* | Scheduled calendar date |
| `interview_time`| `TIME` | NO | | *None* | Scheduled start time |
| `type` | `ENUM('TECHNICAL','HR','ONLINE_TEST')` | NO | | *None* | Assessment categorization |
| `location_link`| `VARCHAR(255)`| YES | | `NULL` | Meeting URL (Zoom/Meet) or physical room |
| `instructions` | `TEXT` | YES | | `NULL` | Candidate preparatory instructions |

---

### 2.9 Table: `notifications`
User alert and notification messages.

| Column Name | Data Type | Nullable | Key | Default | Description |
| :--- | :--- | :---: | :---: | :--- | :--- |
| `notif_id` | `INT` | NO | `PK` | `AUTO_INCREMENT` | Unique identifier for notification |
| `user_id` | `INT` | NO | `FK, IDX` | *None* | References `users(user_id)` ON DELETE CASCADE |
| `message` | `TEXT` | NO | | *None* | Notification text body |
| `is_read` | `BOOLEAN` | NO | `IDX` | `FALSE` | Read acknowledgement boolean |
| `created_at` | `TIMESTAMP` | NO | | `CURRENT_TIMESTAMP` | Dispatch timestamp |

---

### 2.10 Auxiliary Tables (Student Portfolio)
- `education`: Academic degrees, institutions, passing years, and aggregate percentages.
- `skills`: Master catalog of technical capabilities.
- `student_skills`: M:N associative junction table linking `student_id` to `skill_id`.
- `projects`: Candidate software and engineering projects with live links.
- `certifications`: Industry credentials and issuing authorities.
- `experiences`: Prior internships, full-time positions, and descriptions.
