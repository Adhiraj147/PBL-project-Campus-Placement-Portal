# Weekly Progress Report — Week 2
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Period: Week 2 of Project Development

---

## 1. Overview & Objectives

Week 2 focused on converting the conceptual ER model into a production-grade relational database schema implemented in MySQL 8.0. Key priorities included enforcing Third Normal Form (3NF), configuring referential integrity cascade policies (`ON DELETE CASCADE`, `SET NULL`), building table schemas, and producing an exhaustive Data Dictionary for the engineering team.

---

## 2. Day-by-Day Activity Log

| Day | Date | Activities & Technical Milestones | Status |
| :--- | :--- | :--- | :---: |
| **Day 8** | Monday | Designed DDL table definitions for core authentication and user profiles (`users`, `students`, `companies`, `admins`). Enforced BCrypt token length (`VARCHAR(255)`). | Completed |
| **Day 9** | Tuesday | Formulated opportunity listings table (`opportunities`) with automated criteria attributes (`min_cgpa`, `eligible_branches`, `graduation_year_req`). Defined drive state ENUMs. | Completed |
| **Day 10** | Wednesday | Modeled application tracking and auditing layer (`applications`, `application_status_history`, `interviews`). Added composite uniqueness constraint `UNIQUE(student_id, opp_id)` to prevent duplicate attempts. | Completed |
| **Day 11** | Thursday | Engineered dynamic candidate portfolio tables: `education`, `skills`, `student_skills`, `projects`, `certifications`, and `experiences`. Tested 1:N relations with foreign key cascades. | Completed |
| **Day 12** | Friday | Conducted formal 3NF normalization audit. Removed transitive dependencies between company metadata and job opportunities. Tested schema creation on local MySQL instance. | Completed |
| **Day 13** | Saturday | Authored seed data script: provisioned bootstrap system administrator (`admin@college.edu` with verified BCrypt hash) and pre-seeded common industry technical skills. | Completed |
| **Day 14** | Sunday | Compiled complete Data Dictionary (`02_data_dictionary.md`), normalization report (`01_relational_schema_design.md`), and consolidated production script (`schema.sql`). | Completed |

---

## 3. Key Deliverables & Artifacts Generated

1. `schema.sql`: Full production SQL script creating 15 tables with indexes, foreign keys, and bootstrap seed records.
2. `01_relational_schema_design.md`: Normalization walkthrough (1NF $\rightarrow$ 3NF), cascading deletion strategies, and indexing guidelines.
3. `02_data_dictionary.md`: Detailed schema dictionary specifying columns, data types, nullability, defaults, keys, and descriptions.
4. `weekly-progress.md`: Formal weekly milestone log.

---

## 4. Challenges Addressed

- **Orphan Records on Account Deletion**: Mitigated by explicitly declaring `ON DELETE CASCADE` on `users` child tables, while using `ON DELETE SET NULL` on `application_status_history.changed_by` to preserve compliance logs.
- **Floating Point Imprecision in Academic Scores**: Used `DECIMAL(4,2)` for CGPA and `DECIMAL(5,2)` for percentages to avoid standard IEEE-754 binary floating-point roundoff errors during eligibility comparisons.

---

## 5. Next Week Plan (Week 3)

- Implement the Java JDBC database connection infrastructure (`DatabaseConnection.java`).
- Configure driver lifecycle management via static class loader initialization.
- Provide connectivity configurations supporting both local MySQL and remote cloud instances (e.g., Aiven MySQL).
- Author connectivity manuals and verification procedures for module developers.
