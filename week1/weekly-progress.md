# Weekly Progress Report — Week 1
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Period: Week 1 of Project Development

---

## 1. Overview & Objectives

During Week 1, my primary objective was to spearhead the initial database planning, data requirement specification, entity modeling, and architectural coordination for the **Campus Placement and Internship Portal**. I worked closely with module owners Adhiraj (Authentication), Shlok (Student Portal), Saurabh (Recruiter Portal), and Krishna (Admin) to map out end-to-end data pipelines and prevent schema fragmentation early in the lifecycle.

---

## 2. Day-by-Day Activity Log

| Day | Date | Activities & Technical Milestones | Status |
| :--- | :--- | :--- | :---: |
| **Day 1** | Monday | Convened project charter kickoff meeting. Identified core problems in manual placement procedures (spreadsheet duplication, late notices). Defined scope of Module 5 (Database, Integration & Testing). | Completed |
| **Day 2** | Tuesday | Interviewed stakeholders (Students, Corporate HR, TPO Officers) to capture functional data requirements. Identified requirement for multi-table dynamic student resumes (education, projects, certifications, skills). | Completed |
| **Day 3** | Wednesday | Designed high-level Entity-Relationship (ER) model. Identified core entities: `users`, `students`, `companies`, `admins`, `opportunities`, `applications`. Mapped cardinalities and primary/foreign key hierarchies. | Completed |
| **Day 4** | Thursday | Analyzed auxiliary entities for audit logging and scheduling: `application_status_history`, `interviews`, `notifications`. Defined composite uniqueness on `(student_id, opp_id)` to avoid duplicate submissions. | Completed |
| **Day 5** | Friday | Conducted Data Flow Analysis (DFD Level 0 and Level 1). Traced data movement for user registration, automated eligibility checks, job application pipelines, and interview scheduling workflows. | Completed |
| **Day 6** | Saturday | Evaluated database engines and Java connectivity options. Selected MySQL 8.0 (InnoDB) for ACID transaction guarantees, paired with official `mysql-connector-java:8.0.33` JDBC driver. Structured Maven dependency requirements. | Completed |
| **Day 7** | Sunday | Authored Week 1 documentation deliverables (`01_database_system_requirements.md`, `02_entity_relationship_specification.md`, `03_data_flow_and_lifecycle_analysis.md`). Prepared milestone submission for mentor review. | Completed |

---

## 3. Key Deliverables & Artifacts Generated

1. `01_database_system_requirements.md`: Comprehensive system data requirements, non-functional criteria, volume projections, and ACID guarantees.
2. `02_entity_relationship_specification.md`: Conceptual ER diagram (Mermaid), entity dictionary, cardinality definitions, and referential constraints.
3. `03_data_flow_and_lifecycle_analysis.md`: Detailed DFD Level 0 and Level 1 sequences, transaction atomicity designs, and candidate progression state machine.
4. `weekly-progress.md`: Formal weekly milestone log.

---

## 4. Challenges Addressed

- **Data Duplication Risk**: Resolved by strictly separating global authentication credentials (`users`) from specialized role tables (`students`, `companies`, `admins`) using 1:1 foreign keys with `ON DELETE CASCADE`.
- **Race Conditions in Drive Applications**: Formulated database-level constraints `UNIQUE(student_id, opp_id)` rather than relying solely on application-level checks.

---

## 5. Next Week Plan (Week 2)

- Formalize the relational database schema into clean, executable SQL scripts (`schema.sql`).
- Execute Third Normal Form (3NF) normalization across all tables.
- Establish comprehensive Data Dictionary detailing column types, nullability, default values, and foreign key cascades.
- Pre-seed essential lookup and test records (skills catalog, default administrator).
