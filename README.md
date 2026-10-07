# 🎓 Campus Placement & Internship Portal
### 5th Semester Advanced Java Lab Final Project (PBL)

An enterprise-grade, full-stack recruitment and campus placement platform architected in **Pure Java** (MVC Pattern, Servlets, JDBC, DAO Layer, Session Management) with an **Antigravity Glassmorphism** Web UI and an executive **Java Swing Desktop Console**.

---

## 👥 Five-Member Team Division & Contributions

This project strictly adheres to the university 5-member team workload distribution:

| No. | Team Member | GitHub Feature Branch | Primary Modules & Responsibilities |
| :---: | :--- | :--- | :--- |
| **1** | **Adhiraj** | `feature/authentication` | **Module 1: Authentication & User Management**<br>SHA-256 password cryptography, session management (`JSESSIONID`), role-based access control (RBAC), and unauthorized access protection. |
| **2** | **Shlok** | `feature/student-portal` | **Module 2: Student Command Center**<br>Candidate profile management, skills & resume repository, automated CGPA/department eligibility engine, and real-time application tracking. |
| **3** | **Saurabh** | `feature/recruiter-portal` | **Module 3: Recruiter & Company Portal**<br>Company profile, job & internship drive postings, candidate resume screening, shortlisting/rejection pipeline, and live interview scheduling. |
| **4** | **Krishna** | `feature/admin-portal` | **Module 4: Admin & Analytics**<br>TPO Executive dashboard, corporate partner verification, job drive moderation, cohort placement statistics, and visual reporting. |
| **5** | **Himanshu** | `feature/database-integration` | **Module 5: Database, Integration & Testing (System Lead)**<br>MySQL 8.0 schema & relationships, dual-engine JDBC persistence (with zero-setup fallback), in-app notifications, 25-point automated test suite, and embedded HTTP server runtime. |

---

## ⚡ Zero-Dependency 1-Click Quickstart

The project contains a built-in pure Java HTTP Application Server (`com.sun.net.httpserver.HttpServer`). **No external Tomcat download, Maven installation, or MySQL configuration is required** to run and demonstrate the project.

### Option 1: Web Portal (Windows / macOS / Linux)

#### On Windows (Command Prompt / Double Click):
```cmd
compile.bat
run.bat
```

#### On PowerShell:
```powershell
.\compile.ps1
.\run.ps1
```

Once started, open your web browser at:
👉 **`http://localhost:8080/`**

---

### Option 2: Automated Unit & Integration Tests (25 / 25 Tests)
Run the automated test runner verifying all 5 team modules:
```cmd
test.bat
```
*(or `.\test.ps1` in PowerShell)*

---

### Option 3: Java Swing Desktop TPO Client
Satisfies the lab syllabus requirement for Java Foundation Classes / Desktop Applet equivalents:
```cmd
desktop.bat
```
*(or `.\desktop.ps1` in PowerShell)*

---

## 🔑 Demo Login Credentials

For quick evaluation and viva demonstrations, 1-click test credentials are built into the login portal:

| Role | University Email | Password | Preloaded Test Profile |
| :--- | :--- | :--- | :--- |
| **Student** | `himanshu@gmail.com` | `pass123` | Himanshu Yadav (CSE, 9.40 CGPA, Shortlisted for Google SWE) |
| **Student** | `shlok@gmail.com` | `pass123` | Shlok Joshi (CSE, 8.85 CGPA) |
| **Student** | `adhiraj@gmail.com` | `pass123` | Adhiraj Rathore (IT, 8.60 CGPA) |
| **Recruiter** | `recruiter@google.com` | `pass123` | Saurabh Verma (Google India TA Lead) |
| **Recruiter** | `recruiter@microsoft.com` | `pass123` | Elena Rostova (Microsoft University Relations) |
| **Recruiter** | `recruiter@amazon.com` | `pass123` | Rajesh Iyer (AWS Campus Lead) |
| **TPO Admin** | `admin@campus.edu` | `pass123` | Dr. Krishna Sharma (Head of Placement Cell) |

---

## 🏗️ Repository Architecture

```
campus-placement-portal/
├── src/main/java/com/placement/
│   ├── auth/                # Module 1 (Adhiraj): AuthService, AuthServlet
│   ├── student/             # Module 2 (Shlok): StudentService, StudentServlet
│   ├── recruiter/           # Module 3 (Saurabh): RecruiterService, RecruiterServlet
│   ├── admin/               # Module 4 (Krishna): AdminService, AdminServlet
│   ├── db/                  # Module 5 (Himanshu): DBConnection, InMemoryDB, DBUtils
│   ├── dao/                 # Module 5 (Himanshu): UserDAO, StudentDAO, CompanyDAO, JobDAO, etc.
│   ├── model/               # Entity POJOs (User, StudentProfile, Job, Application, etc.)
│   ├── server/              # Pure Java Embedded HTTP Server & Servlet Abstraction
│   ├── gui/                 # Java Swing Placement Officer Desktop Console
│   └── test/                # 25-Point Automated Test Suite Runner
├── src/main/webapp/
│   ├── assets/              # High-definition 3D platform hero visuals
│   ├── css/style.css        # Antigravity Glassmorphism Design System CSS
│   ├── js/app.js            # Particle canvas, native HTML5 charts, and REST client
│   ├── index.html           # Landing page with active drives & ticker
│   ├── login.html           # 1-click quick demo authentication hub
│   ├── register.html        # Dynamic candidate & corporate registration
│   ├── student-dashboard.html   # Student application & eligibility dashboard
│   ├── recruiter-dashboard.html # Recruiter drives & applicant review pipeline
│   ├── admin-dashboard.html     # TPO executive intelligence & verification hub
│   └── WEB-INF/web.xml      # Standard Java EE Deployment Descriptor
├── sql/
│   ├── schema.sql           # Full MySQL 8.0 relational schema with foreign keys
│   └── seed-data.sql        # Realistic university & corporate seed data
├── docs/
│   ├── architecture/SYSTEM_ARCHITECTURE.md  # Detailed MVC diagrams & sequence flow
│   ├── er-diagram/ER_DIAGRAM.md            # Entity-relationship schema & tables
│   └── GITHUB_WORKFLOW_GUIDE.md            # Branch strategy & member commit histories
├── pom.xml                  # Maven WAR configuration (for external Tomcat deployment)
├── compile.bat / compile.ps1 # One-click compile scripts
├── run.bat / run.ps1         # One-click web server run scripts
├── test.bat / test.ps1       # One-click test runner scripts
├── desktop.bat / desktop.ps1 # One-click Swing GUI scripts
└── README.md
```

---

## 🛡️ Key Design & Engineering Highlights

1. **Dual-Engine Persistence:**
   - Detects if an external MySQL 8.0 server is running on `localhost:3306`.
   - If MySQL is not present, it automatically runs using an in-memory thread-safe relational database with pre-populated realistic seed data.
2. **Eligibility Engine:**
   - Evaluates a student's CGPA and department against the recruiter's criteria before permitting submission.
3. **Session & Security Integrity:**
   - Issues `JSESSIONID` cookies, hashes all passwords using SHA-256, and validates caller roles on protected endpoints.
4. **Antigravity Glassmorphism UI:**
   - Deep obsidian space aesthetic (`#07090e`), luminous cyan accents (`#00f0ff`), interactive constellation background, and pure HTML5 canvas charts.

---

## 🎓 5th Semester Lab Exam / Viva Defense Guide

1. **Q: Why are Servlets used instead of plain CGI or JSP alone?**
   - *A:* Servlets act as Controllers in the MVC architecture. Unlike CGI (which spawns an OS process per request), Servlets use lightweight Java threads managed by a thread pool (`HttpServer` / Tomcat), providing high throughput and stateful session management.
2. **Q: How does the DAO pattern help here?**
   - *A:* Data Access Objects isolate business logic from database query syntax. If we swap MySQL with PostgreSQL or an in-memory mock engine, the business services (`StudentService`, `RecruiterService`) require zero modifications.
3. **Q: How is session tracking handled in pure Java?**
   - *A:* Via HTTP cookies (`JSESSIONID`). The server issues a cryptographically random session token stored in a thread-safe map (`ConcurrentHashMap`), associating the user's authentication state with subsequent requests.
4. **Q: What is the purpose of the Swing desktop client?**
   - *A:* It demonstrates Java Foundation Classes (JFC/Swing) and cross-platform GUI architecture, enabling the Placement Officer to monitor recruitment drives directly from a native desktop application.
