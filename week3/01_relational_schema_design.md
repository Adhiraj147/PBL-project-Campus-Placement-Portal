# Relational Schema Design & 3NF Normalization Analysis
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 3 Technical Specification

---

## 1. Executive Summary

A critical engineering responsibility within the Campus Placement and Internship Portal is establishing a rigorous, normalized relational schema that guarantees data integrity, eliminates data anomalies (insertion, update, and deletion anomalies), and supports high-throughput concurrent access.

This technical specification documents the formal decomposition of the system's data model into **Third Normal Form (3NF)** across all 15 operational tables.

---

## 2. Normalization Methodology & Proofs

### 2.1 First Normal Form (1NF) Compliance
**Rule**: All column values must be atomic; no repeating groups, comma-delimited arrays, or nested records are allowed in fundamental relations.

* **Identification of Violation in Legacy Systems**:
  In typical legacy placement registries, student skills and past academic records are stored as multi-valued comma-separated strings inside a single student row (e.g., `skills = "Java, Python, React"`). This prevents indexing, creates inconsistent search queries, and violates 1NF.
* **Resolution**:
  - Skills are extracted into an independent dictionary table `skills(skill_id, skill_name)` and a junction table `student_skills(student_id, skill_id)`.
  - Academic history is partitioned into individual rows in `education(edu_id, student_id, degree, institution, passing_year, percentage)`.
  - Every attribute across all tables contains strictly atomic scalar values.

### 2.2 Second Normal Form (2NF) Compliance
**Rule**: The schema must be in 1NF, and all non-key attributes must be fully functionally dependent on the entire primary key (no partial dependencies on composite keys).

* **Analysis of Composite Key Entities**:
  - In `student_skills(student_id, skill_id)`, the primary key is composite `(student_id, skill_id)`. There are no non-key attributes; thus no partial dependency exists.
  - In `applications(application_id, student_id, opp_id, status, applied_at)`, the primary key is a surrogate `application_id`. The candidate key is `(student_id, opp_id)`. All non-key attributes (`status`, `applied_at`) depend on the specific application instance as a whole, not partially on `student_id` or `opp_id` alone.

### 2.3 Third Normal Form (3NF) Compliance
**Rule**: The schema must be in 2NF, and no non-key attribute is transitively dependent on the primary key (no $X \rightarrow Y$ where $Y \rightarrow Z$ and $Z$ is a non-prime attribute).

* **Authentication & Role Specialization Separation**:
  - If `students` or `companies` stored credentials directly, email changes would create transitive anomalies.
  - Solution: `users` isolates identity (`user_id`, `email`, `password_hash`, `role`). `students`, `companies`, and `admins` hold 1:1 foreign keys on `user_id`. Company attributes (`company_name`, `website`) depend solely on `company_id`. Student academic attributes (`cgpa`, `roll_no`) depend solely on `student_id`.
* **Application Status Audit Separation**:
  - Rather than storing status remarks and updater metadata in `applications`, updates create immutable audit rows in `application_status_history(history_id, application_id, status, remarks, changed_by, changed_at)`.
  - This preserves historical immutability and eliminates transitive dependencies.

---

## 3. Functional Dependency Map

The following functional dependencies govern the primary entities:

$$
\begin{aligned}
\text{users}: & \quad \text{user\_id} \rightarrow \{\text{email}, \text{password\_hash}, \text{role}, \text{status}, \text{created\_at}\} \\
& \quad \text{email} \rightarrow \{\text{user\_id}, \text{password\_hash}, \text{role}, \text{status}, \text{created\_at}\} \\
\text{students}: & \quad \text{student\_id} \rightarrow \{\text{user\_id}, \text{first\_name}, \text{last\_name}, \text{roll\_no}, \text{branch}, \text{graduation\_year}, \text{cgpa}, \text{phone}, \text{resume\_url}, \text{profile\_completed}\} \\
& \quad \text{roll\_no} \rightarrow \{\text{student\_id}, \dots\} \\
\text{companies}: & \quad \text{company\_id} \rightarrow \{\text{user\_id}, \text{company\_name}, \text{description}, \text{website}, \text{industry}, \text{location}, \text{contact\_person}\} \\
\text{opportunities}: & \quad \text{opp\_id} \rightarrow \{\text{company\_id}, \text{title}, \text{type}, \text{description}, \text{location}, \text{work\_mode}, \text{salary\_stipend}, \text{min\_cgpa}, \text{eligible\_branches}, \text{graduation\_year\_req}, \text{deadline}, \text{status}\} \\
\text{applications}: & \quad \text{application\_id} \rightarrow \{\text{student\_id}, \text{opp\_id}, \text{status}, \text{applied\_at}\} \\
& \quad \{\text{student\_id}, \text{opp\_id}\} \rightarrow \{\text{application\_id}, \text{status}, \text{applied\_at}\}
\end{aligned}
$$

Because every left-hand determinant in these dependencies is a superkey, the schema strictly satisfies **Boyce-Codd Normal Form (BCNF)** and **Third Normal Form (3NF)**.

---

## 4. Referential Integrity & Cascade Strategies

| Parent Table | Child Table | Foreign Key Column | On Delete Action | On Update Action | Architectural Rationale |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `users` | `students` | `user_id` | `CASCADE` | `CASCADE` | Profile cannot exist without parent identity. |
| `users` | `companies` | `user_id` | `CASCADE` | `CASCADE` | Company metadata purged upon user deletion. |
| `users` | `admins` | `user_id` | `CASCADE` | `CASCADE` | Admin metadata purged upon account removal. |
| `companies` | `opportunities` | `company_id` | `CASCADE` | `CASCADE` | Deleting company purges all associated drives. |
| `students` | `applications` | `student_id` | `CASCADE` | `CASCADE` | Candidate application history purged upon account teardown. |
| `opportunities`| `applications` | `opp_id` | `CASCADE` | `CASCADE` | Applications invalid if job opening is deleted. |
| `applications` | `application_status_history` | `application_id` | `CASCADE` | `CASCADE` | Historical lifecycle bound to parent application. |
| `users` | `application_status_history` | `changed_by` | `SET NULL` | `CASCADE` | Retain audit trail even if staff user is deleted. |
| `applications` | `interviews` | `application_id` | `CASCADE` | `CASCADE` | Interview schedules tied to specific application. |
| `students` | `education` | `student_id` | `CASCADE` | `CASCADE` | Academic records cascaded with candidate. |
| `students` | `projects` | `student_id` | `CASCADE` | `CASCADE` | Projects cascaded with candidate. |
| `students` | `experiences` | `student_id` | `CASCADE` | `CASCADE` | Experiences cascaded with candidate. |
| `students` | `certifications`| `student_id`| `CASCADE` | `CASCADE` | Certifications cascaded with candidate. |
| `students` | `student_skills`| `student_id`| `CASCADE` | `CASCADE` | Junction entries cleared upon candidate deletion. |
| `skills` | `student_skills`| `skill_id` | `CASCADE` | `CASCADE` | Junction entries cleared upon master skill deletion. |

---

## 5. Storage Engine & Concurrency Configuration

- **Storage Engine**: `ENGINE=InnoDB` across all 15 tables.
- **Row-Level Locking**: InnoDB provides fine-grained row-level locking for concurrent writes on `applications` and `application_status_history` without locking entire tables.
- **ACID Transactions**: Enables multi-statement atomic operations for complex registration and application workflows.
- **Collation**: `utf8mb4_unicode_ci` ensures full international Unicode character support (emojis, accents, special characters).
