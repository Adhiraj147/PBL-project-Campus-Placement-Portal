# Campus Placement & Internship Portal
## Module 5: Database Architecture, Integration Layer & System Testing
### 5th Semester B.Tech Project-Based Learning (PBL) — Advanced Java Lab

---

### Module Lead & Author Profile
- **Name**: Himanshu Yadav
- **Assigned Module**: **Module 5: Database Architecture, Integration Layer & System Testing**
- **GitHub**: [@XXHimanshuXX](https://github.com/XXHimanshuXX)
- **LinkedIn**: [Himanshu Yadav Profile](https://www.linkedin.com/in/himanshu-yadav-112ba6376)
- **Email**: [himanshu.yadav060107@gmail.com](mailto:himanshu.yadav060107@gmail.com)
- **Feature Branch**: [`feature/Himanshu`](https://github.com/Adhiraj147/PBL-project-Campus-Placement-Portal/tree/feature/Himanshu)

---

## 1. Executive Summary

The **Campus Placement and Internship Portal** is an enterprise-grade recruitment management system engineered using **Pure Java (Java Servlets 4.0, JSP 2.3, JDBC, MySQL 8.0, MVC Architecture, BCrypt, and Apache Maven)**.

Within our 5-member engineering team, my charter as the owner of **Module 5** encompassed:
1. **Relational Database Architecture**: Conceptual ER modeling, 3NF normalization, DDL scripts, constraints, cascade deletion policies, and data dictionaries.
2. **JDBC Infrastructure**: Centralized thread-safe connection management (`DatabaseConnection.java`), cloud SSL security, and dynamic environment configuration.
3. **Core & Transactional DAO Layer**: Pure JDBC persistence layer for all entities (`users`, `students`, `companies`, `opportunities`, `applications`, `admins`) with atomic transaction support and complete protection against SQL injection.
4. **Service Integration & Automated Eligibility Engine**: Business service layer (`EligibilityService.java`) enforcing automated CGPA, branch, and profile completion criteria before candidate application commitment.
5. **Quality Assurance & DevOps Packaging**: Comprehensive 25-point automated verification test runner (`DatabaseIntegrationTest.java`), production Maven build configuration (`pom.xml`), and multi-stage Docker containerization (`Dockerfile`).

---

## 2. 7-Week Development Roadmap & Deliverables Index

```
feature/Himanshu/
├── README.md                                      # Consolidated Module 5 Documentation & Portfolio
├── .gitignore                                     # Git ignore rules for build artifacts & IDE configs
├── week1/                                         # Week 1: Database Planning & Entity Modeling
│   ├── 01_database_system_requirements.md         # Data requirements, ACID guarantees, concurrency
│   ├── 02_entity_relationship_specification.md    # Conceptual ER diagram, cardinality, entity catalog
│   ├── 03_data_flow_and_lifecycle_analysis.md     # Level 0 & Level 1 DFDs, sequence diagrams
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 1 - 7)
├── week2/                                         # Week 2: Relational Schema & Data Dictionary
│   ├── schema.sql                                 # Production MySQL DDL script (15 normalized tables)
│   ├── 01_relational_schema_design.md             # 3NF normalization, indexing, cascade policies
│   ├── 02_data_dictionary.md                      # Exhaustive dictionary of all tables and columns
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 8 - 14)
├── week3/                                         # Week 3: JDBC Infrastructure & Connectivity
│   ├── DatabaseConnection.java                    # Centralized JDBC connection factory class
│   ├── 01_jdbc_architecture_and_connection_management.md # Driver lifecycle, sockets, SSL parameters
│   ├── 02_database_connectivity_guide.md          # Setup guide for Local MySQL, Cloud DB & Docker
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 15 - 21)
├── week4/                                         # Week 4: Core Persistence Layer (User, Student, Company)
│   ├── dao/
│   │   ├── UserDAO.java                           # User DAO interface (authentication & registration)
│   │   ├── UserDAOImpl.java                       # Production User DAO with atomic transactions
│   │   ├── StudentDAO.java                        # Student profile DAO interface
│   │   ├── StudentDAOImpl.java                    # Student DAO with composite portfolio hydration
│   │   ├── CompanyDAO.java                        # Company DAO interface
│   │   └── CompanyDAOImpl.java                    # Company DAO implementation
│   ├── 01_dao_pattern_and_crud_architecture.md    # DAO pattern principles & separation of concerns
│   ├── 02_sql_injection_defense_and_transactions.md # PreparedStatement security & transaction atomicity
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 22 - 28)
├── week5/                                         # Week 5: Transactional & Operational DAOs
│   ├── dao/
│   │   ├── OpportunityDAO.java                    # Opportunity DAO interface (drives & jobs)
│   │   ├── OpportunityDAOImpl.java                # Opportunity DAO with keyword search engine
│   │   ├── ApplicationDAO.java                    # Application DAO interface
│   │   ├── ApplicationDAOImpl.java                # Application DAO with status history ledgering
│   │   ├── AdminDAO.java                          # Admin DAO interface
│   │   └── AdminDAOImpl.java                      # Admin DAO with aggregate placement KPI queries
│   ├── 01_transactional_workflow_persistence.md   # Candidate progression state machine & audit log
│   ├── 02_admin_analytics_query_optimization.md   # SQL query optimization and latency benchmarks
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 29 - 35)
├── week6/                                         # Week 6: Service Integration & Eligibility Engine
│   ├── service/
│   │   └── EligibilityService.java                # Automated candidate eligibility validation engine
│   ├── 01_eligibility_engine_architecture.md      # Rule hierarchy decision tree & rejection reasons
│   ├── 02_cross_module_integration_guide.md       # Public API contracts for Adhiraj, Shlok, Saurabh
│   └── weekly-progress.md                         # Day-by-Day Activity Log (Days 36 - 42)
└── week7/                                         # Week 7: Testing, DevOps & Containerization
    ├── test/
    │   └── DatabaseIntegrationTest.java           # 25-Point automated integration verification suite
    ├── pom.xml                                    # Production Maven build & plugin configuration
    ├── Dockerfile                                 # Multi-stage Docker containerization script
    ├── 01_system_testing_and_verification_report.md # Comprehensive QA verification report (100% Pass)
    ├── 02_devops_docker_deployment_manual.md      # Deployment manual (Maven, Docker, Tomcat, Cloud)
    └── weekly-progress.md                         # Day-by-Day Activity Log (Days 43 - 49)
```

---

## 3. System Architecture & Module Integration

```mermaid
flowchart TD
    subgraph Team Modules
        M1["Module 1: Authentication & RBAC (Adhiraj)"]
        M2["Module 2: Student Portal (Shlok)"]
        M3["Module 3: Recruiter Portal (Saurabh)"]
        M4["Module 4: Admin Portal (Krishna)"]
    end

    subgraph Module 5: Database & Integration (Himanshu)
        SVC["EligibilityService (Business Rule Engine)"]
        DAO["DAO Layer (UserDAO, StudentDAO, CompanyDAO, OpportunityDAO, ApplicationDAO, AdminDAO)"]
        DBC["DatabaseConnection Factory (JDBC Driver / SSL / Pooling)"]
        QA["25-Point Automated Verification Suite (DatabaseIntegrationTest)"]
    end

    subgraph Persistence
        MySQL[("MySQL 8.0 InnoDB (Local / Aiven Cloud)")]
    end

    M1 -->|Uses UserDAO| DAO
    M2 -->|Validates via| SVC
    M2 -->|Uses StudentDAO & ApplicationDAO| DAO
    M3 -->|Uses CompanyDAO & OpportunityDAO| DAO
    M4 -->|Uses AdminDAO| DAO
    SVC -->|Evaluates Domain Models| DAO
    DAO -->|Acquires Connections| DBC
    DBC -->|Type 4 Network Sockets| MySQL
    QA -.->|Verifies Integrity| DAO
    QA -.->|Verifies Logic| SVC
```

---

## 4. Key Highlights & Engineering Achievements

1. **Strict 3NF Normalization**: Complete elimination of insertion, update, and deletion anomalies across 15 relational tables.
2. **Zero SQL Injection**: 100% of all SQL interactions use parameterized `PreparedStatement`.
3. **Atomic Multi-Table Transactions**: Implemented transactional rollback mechanics (`setAutoCommit(false)`, `commit()`, `rollback()`) across user onboarding and application status workflows.
4. **Automated Eligibility Screening**: Prevents invalid candidate applications by enforcing a 4-tier validation check (Profile Completeness, Minimum CGPA, Graduation Batch Year, Eligible Department Whitelist).
5. **Production Docker Packaging**: Multi-stage Dockerfile compiling Java WAR artifacts and deploying to a minimal Apache Tomcat 9 container.
6. **25-Point Test Suite**: Automated test runner verifying connectivity, persistence, security, and business rules with a **100% Pass Rate**.

---

## 5. Quick Start & Execution

### 5.1 Run the Automated Test Suite
Ensure MySQL connectivity is active (defaults point to cloud MySQL):
```bash
# Compile and run test suite
javac -d bin -cp "src/main/java;lib/*" week7/test/DatabaseIntegrationTest.java
java -cp "bin;lib/*" com.placement.test.DatabaseIntegrationTest
```

### 5.2 Build & Run with Maven
```bash
mvn clean package -DskipTests
mvn tomcat7:run
```
Access at: `http://localhost:8080/campus-placement-portal`

### 5.3 Run via Docker
```bash
docker build -t campus-placement-portal .
docker run -p 8080:8080 campus-placement-portal
```
Access at: `http://localhost:8080/`
