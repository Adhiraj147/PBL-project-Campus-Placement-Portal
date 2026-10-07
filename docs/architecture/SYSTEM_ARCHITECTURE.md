# System Architecture & Technical Specification
## Campus Placement and Internship Portal (Advanced Java Final Project)

---

### 1. Architectural Overview

The **Campus Placement and Internship Portal** is built using the enterprise **Model-View-Controller (MVC)** architectural pattern, strictly separating data persistence, business logic processing, and presentation layers.

```mermaid
flowchart TD
    Client["Client Browser / HTTP Client"]
    Desktop["Java Swing TPO Desktop Client"]
    
    subgraph Presentation_Layer["Presentation Layer (Antigravity Glassmorphism)"]
        Index["index.html (Landing & Drives)"]
        Login["login.html (Auth & Quick Demo)"]
        StudentUI["student-dashboard.html"]
        RecruiterUI["recruiter-dashboard.html"]
        AdminUI["admin-dashboard.html"]
    end

    subgraph Controller_Layer["Controller & Routing Layer (Java Servlets)"]
        Router["PlacementServer / Standard Servlet Container"]
        AuthSrv["com.placement.auth.AuthServlet (Adhiraj)"]
        StudentSrv["com.placement.student.StudentServlet (Shlok)"]
        RecruiterSrv["com.placement.recruiter.RecruiterServlet (Saurabh)"]
        AdminSrv["com.placement.admin.AdminServlet (Krishna)"]
    end

    subgraph Business_Service_Layer["Business Service Layer"]
        AuthService["AuthService (Password Hashing, RBAC)"]
        StudentService["StudentService (Eligibility Engine)"]
        RecruiterService["RecruiterService (Drive & Pipeline)"]
        AdminService["AdminService (Analytics & Moderation)"]
    end

    subgraph Persistence_Layer["Persistence Layer (DAO & Dual-Engine JDBC)"]
        DAOs["UserDAO | StudentDAO | CompanyDAO | JobDAO | ApplicationDAO | InterviewDAO | NotificationDAO"]
        DualEngine["DBConnection (Himanshu)"]
        MySQL[("MySQL 8.0 Relational DB")]
        InMemory[("Thread-Safe In-Memory Relational Engine")]
    end

    Client --> Presentation_Layer
    Presentation_Layer -->|JSON / REST| Router
    Desktop --> Business_Service_Layer
    
    Router --> AuthSrv
    Router --> StudentSrv
    Router --> RecruiterSrv
    Router --> AdminSrv

    AuthSrv --> AuthService
    StudentSrv --> StudentService
    RecruiterSrv --> RecruiterService
    AdminSrv --> AdminService

    AuthService --> DAOs
    StudentService --> DAOs
    RecruiterService --> DAOs
    AdminService --> DAOs

    DAOs --> DualEngine
    DualEngine -->|Production Mode| MySQL
    DualEngine -->|Zero-Config Lab Mode| InMemory
```

---

### 2. Five-Module Work Distribution Matrix

| Module | Team Member | Git Feature Branch | Key Java Packages & Responsibilities |
| :--- | :--- | :--- | :--- |
| **Module 1** | **Adhiraj** | `feature/authentication` | `com.placement.auth.*`<br>• Multi-role authentication (Student, Recruiter, Admin)<br>• SHA-256 password cryptography<br>• Session tracking via `JSESSIONID`<br>• Role-based access control (RBAC) & unauthorized-access protection |
| **Module 2** | **Shlok** | `feature/student-portal` | `com.placement.student.*`<br>• Student dashboard & credentials management<br>• Automated CGPA & department eligibility evaluation<br>• Job/internship application submission & withdrawal<br>• Real-time application pipeline tracking |
| **Module 3** | **Saurabh** | `feature/recruiter-portal` | `com.placement.recruiter.*`<br>• Corporate profile management<br>• Creation, moderation, and closing of campus drives<br>• Candidate screening, resume evaluation, shortlist/reject workflow<br>• Interview scheduling (virtual link, date, time) |
| **Module 4** | **Krishna** | `feature/admin-portal` | `com.placement.admin.*`<br>• TPO Executive administration dashboard<br>• Corporate partner verification & approval<br>• Job drive moderation & policy checks<br>• Real-time cohort analytics (branch-wise placement, average CTC, peak package) |
| **Module 5** | **Himanshu** | `feature/database-integration` | `com.placement.db.*`, `com.placement.dao.*`, `com.placement.server.*`, `com.placement.test.*`<br>• MySQL 8.0 schema & relationship design<br>• Dual-engine JDBC persistence (with zero-setup fallback)<br>• Complete DAO layer for all 7 entities<br>• In-app notification dispatcher<br>• 25-point automated unit & integration test runner<br>• Pure Java embedded HTTP server runtime |

---

### 3. Request-Response Lifecycle Sequence

```mermaid
sequenceDiagram
    autonumber
    actor Student as Student Candidate (Shlok)
    participant UI as student-dashboard.html
    participant Server as PlacementServer
    participant Servlet as StudentServlet
    participant Service as StudentService
    participant DAO as ApplicationDAO / JobDAO
    participant DB as Dual-Engine Persistence (Himanshu)

    Student->>UI: Clicks "Apply Now" on Google SWE Drive
    UI->>Server: POST /api/student/apply (jobId, coverNote) with Cookie JSESSIONID
    Server->>Servlet: Delegates to StudentServlet.doPost()
    Servlet->>Servlet: Validates Student Session & Role
    Servlet->>Service: applyForJob(studentId, jobId, coverNote)
    Service->>DAO: Check CGPA (student.cgpa >= job.minCgpa) & Branch match
    DAO->>DB: Query Student & Job records
    DB-->>DAO: Returns CGPA: 9.40, Min Required: 8.50 (Eligible)
    Service->>DAO: Check for duplicate application
    DAO->>DB: INSERT into applications (status='APPLIED')
    Service->>DB: INSERT into notifications ("Application Submitted")
    Service-->>Servlet: Returns created Application object
    Servlet-->>Server: Generates JSON 200 OK
    Server-->>UI: { "success": true, "message": "Application submitted!" }
    UI-->>Student: Displays glowing green success toast and refreshes pipeline
```
