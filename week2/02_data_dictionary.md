# Comprehensive Data Dictionary
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Table: `users`
Represents the base authentication and role-based security account for all platform principals.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `user_id` | INT | NO | AUTO_INCREMENT | PK | Unique identifier for each system account |
| `email` | VARCHAR(100) | NO | NULL | UK | Unique login email address |
| `password_hash`| VARCHAR(255) | NO | NULL | - | BCrypt salt + hash token |
| `role` | ENUM('STUDENT','RECRUITER','ADMIN') | NO | NULL | - | User role for RBAC filters |
| `status` | ENUM('ACTIVE','PENDING','INACTIVE')| NO | 'ACTIVE' | - | Account moderation status |
| `created_at` | TIMESTAMP | NO | CURRENT_TIMESTAMP | - | Account creation timestamp |

---

## 2. Table: `students`
Captures academic and biographical profile information for candidate students.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `student_id` | INT | NO | AUTO_INCREMENT | PK | Unique student record identifier |
| `user_id` | INT | YES | NULL | UK, FK | Foreign key referencing `users(user_id)` |
| `first_name` | VARCHAR(50) | NO | NULL | - | Student's given name |
| `last_name` | VARCHAR(50) | NO | NULL | - | Student's family name |
| `roll_no` | VARCHAR(20) | YES | NULL | UK | University roll / registration number |
| `branch` | VARCHAR(50) | YES | NULL | - | Academic department (e.g., CSE, IT, ECE) |
| `graduation_year` | INT | YES | NULL | - | Expected year of degree completion |
| `cgpa` | DECIMAL(4,2) | YES | NULL | - | Cumulative Grade Point Average (0.00-10.00)|
| `phone` | VARCHAR(15) | YES | NULL | - | Contact mobile number |
| `resume_url` | VARCHAR(255) | YES | NULL | - | Web link or file path to uploaded resume |
| `profile_completed`| BOOLEAN | YES | FALSE | - | Flag indicating profile verification status |

---

## 3. Table: `companies`
Stores corporate details and recruitment coordinator profiles.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `company_id` | INT | NO | AUTO_INCREMENT | PK | Unique company identifier |
| `user_id` | INT | YES | NULL | UK, FK | Foreign key referencing `users(user_id)` |
| `company_name` | VARCHAR(100) | NO | NULL | - | Registered corporate business name |
| `description` | TEXT | YES | NULL | - | Company overview and mission statement |
| `website` | VARCHAR(100) | YES | NULL | - | Official corporate website URL |
| `industry` | VARCHAR(50) | YES | NULL | - | Industry sector (e.g., Fintech, Edtech) |
| `location` | VARCHAR(100) | YES | NULL | - | Primary office location / headquarters |
| `contact_person`| VARCHAR(100) | YES | NULL | - | Name of recruiting HR lead |

---

## 4. Table: `admins`
Stores system administration personnel credentials.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `admin_id` | INT | NO | AUTO_INCREMENT | PK | Unique administrator identifier |
| `user_id` | INT | YES | NULL | UK, FK | Foreign key referencing `users(user_id)` |
| `name` | VARCHAR(100) | NO | NULL | - | Administrator full name |
| `employee_id` | VARCHAR(20) | YES | NULL | UK | Faculty / Staff institutional ID |

---

## 5. Table: `opportunities`
Houses job and internship recruitment drives published by companies.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `opp_id` | INT | NO | AUTO_INCREMENT | PK | Unique opportunity identifier |
| `company_id` | INT | YES | NULL | FK | Foreign key referencing `companies(company_id)` |
| `title` | VARCHAR(100) | NO | NULL | - | Job role title (e.g., Software Engineer) |
| `type` | ENUM('JOB','INTERNSHIP') | NO | NULL | - | Opportunity classification |
| `description` | TEXT | NO | NULL | - | Comprehensive job description & duties |
| `location` | VARCHAR(100) | YES | NULL | - | Work location city |
| `work_mode` | ENUM('ONSITE','REMOTE','HYBRID') | YES | 'ONSITE' | - | Operational arrangement |
| `salary_stipend` | VARCHAR(50) | YES | NULL | - | Offered compensation or stipend |
| `min_cgpa` | DECIMAL(4,2) | YES | 0.00 | - | Minimum CGPA cutoff for application |
| `eligible_branches`| VARCHAR(255) | YES | NULL | - | Comma-delimited list of eligible branches |
| `graduation_year_req`| INT | YES | NULL | - | Targeted graduation batch year |
| `required_skills`| VARCHAR(255) | YES | NULL | - | Comma-delimited expected technologies |
| `deadline` | DATE | YES | NULL | - | Final date for accepting applications |
| `status` | ENUM('OPEN','CLOSED') | YES | 'OPEN' | - | Active drive recruitment state |
| `created_at` | TIMESTAMP | NO | CURRENT_TIMESTAMP | - | Date opportunity was posted |

---

## 6. Table: `applications`
Junction table tracking candidate submissions against opportunities.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `application_id` | INT | NO | AUTO_INCREMENT | PK | Unique application instance identifier |
| `student_id` | INT | YES | NULL | FK | Foreign key referencing `students(student_id)` |
| `opp_id` | INT | YES | NULL | FK | Foreign key referencing `opportunities(opp_id)` |
| `status` | ENUM('APPLIED','UNDER_REVIEW','SHORTLISTED','INTERVIEW_SCHEDULED','SELECTED','REJECTED') | YES | 'APPLIED' | - | Current recruitment pipeline status |
| `applied_at` | TIMESTAMP | NO | CURRENT_TIMESTAMP | - | Timestamp when application was placed |

*Constraint*: `UNIQUE(student_id, opp_id)` strictly enforces that a candidate may only apply once per opportunity.

---

## 7. Table: `application_status_history`
Audit ledger capturing state transitions across candidate applications.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `history_id` | INT | NO | AUTO_INCREMENT | PK | Unique audit entry identifier |
| `application_id`| INT | YES | NULL | FK | Foreign key referencing `applications(application_id)` |
| `status` | ENUM(...) | YES | NULL | - | New status applied to application |
| `remarks` | TEXT | YES | NULL | - | Evaluator feedback or internal review notes |
| `changed_by` | INT | YES | NULL | FK | Foreign key referencing `users(user_id)` |
| `changed_at` | TIMESTAMP | NO | CURRENT_TIMESTAMP | - | Timestamp of the state modification |

---

## 8. Table: `interviews`
Scheduled evaluation rounds for shortlisted candidates.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `interview_id` | INT | NO | AUTO_INCREMENT | PK | Unique interview session identifier |
| `application_id`| INT | YES | NULL | FK | Foreign key referencing `applications(application_id)` |
| `interview_date`| DATE | NO | NULL | - | Scheduled calendar date of interview |
| `interview_time`| TIME | NO | NULL | - | Scheduled start time |
| `type` | ENUM('TECHNICAL','HR','ONLINE_TEST') | NO | NULL | - | Interview round format |
| `location_link` | VARCHAR(255) | YES | NULL | - | Google Meet/Zoom link or campus room |
| `instructions` | TEXT | YES | NULL | - | Instructions for candidate preparation |

---

## 9. Table: `notifications`
Direct asynchronous notification queue for user updates.

| Column | Type | Nullable | Default | Key | Description |
| :--- | :--- | :---: | :---: | :---: | :--- |
| `notif_id` | INT | NO | AUTO_INCREMENT | PK | Unique notification identifier |
| `user_id` | INT | YES | NULL | FK | Foreign key referencing `users(user_id)` |
| `message` | TEXT | NO | NULL | - | Notification notification content |
| `is_read` | BOOLEAN | YES | FALSE | - | Flag indicating whether user viewed alert |
| `created_at` | TIMESTAMP | NO | CURRENT_TIMESTAMP | - | Dispatch timestamp |

---

## 10. Tables: `education`, `skills`, `student_skills`, `projects`, `certifications`, `experiences`
Dynamic profile portfolio components:
- `education`: `(edu_id, student_id, degree, institution, passing_year, percentage)`
- `skills`: `(skill_id, skill_name)` with unique name constraint.
- `student_skills`: `(student_id, skill_id)` composite primary key.
- `projects`: `(project_id, student_id, title, description, link)`
- `certifications`: `(cert_id, student_id, name, authority, date)`
- `experiences`: `(exp_id, student_id, company, role, start_date, end_date, description)`
