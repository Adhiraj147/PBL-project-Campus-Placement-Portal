# 5-Member GitHub Workflow & Contribution Plan
## PBL 5th Semester Lab Final Project

This document specifies the exact branching model, feature ownership, and commit history for all five team members as established in the Project Charter.

---

### 1. Branch Strategy & Team Allocations

| Team Member | Git Feature Branch | Target Area |
| :--- | :--- | :--- |
| **Adhiraj** | `feature/authentication` | `com.placement.auth.*`, `login.html`, `register.html` |
| **Shlok** | `feature/student-portal` | `com.placement.student.*`, `student-dashboard.html` |
| **Saurabh** | `feature/recruiter-portal` | `com.placement.recruiter.*`, `recruiter-dashboard.html` |
| **Krishna** | `feature/admin-portal` | `com.placement.admin.*`, `admin-dashboard.html` |
| **Himanshu** | `feature/database-integration` | `com.placement.db.*`, `com.placement.dao.*`, `schema.sql`, test suite, server runner |

---

### 2. Standard Feature Branch Workflow

```bash
# 1. Clone the central university repository
git clone https://github.com/your-college-org/campus-placement-portal.git
cd campus-placement-portal

# 2. Switch to your designated feature branch
git checkout -b feature/<your-assigned-branch>

# 3. Implement your module changes and commit with standardized prefix
git add .
git commit -m "feat: implement <feature-description>"

# 4. Push branch to GitHub
git push -u origin feature/<your-assigned-branch>

# 5. Open Pull Request on GitHub to main branch with description
```

---

### 3. Example Commit Histories (Matching Project Submission PDF)

#### Member 1: Adhiraj (`feature/authentication`)
```
commit 1: feat: create login page
commit 2: feat: implement student authentication
commit 3: feat: add recruiter authentication
commit 4: feat: add admin authentication
commit 5: feat: implement session management
commit 6: fix: prevent unauthorized dashboard access
```

#### Member 2: Shlok (`feature/student-portal`)
```
commit 1: feat: create student dashboard
commit 2: feat: add student profile
commit 3: feat: add skills and education
commit 4: feat: implement resume upload
commit 5: feat: add job listing
commit 6: feat: implement job application
commit 7: feat: add application tracking
```

#### Member 3: Saurabh (`feature/recruiter-portal`)
```
commit 1: feat: create recruiter dashboard
commit 2: feat: add company profile
commit 3: feat: implement job posting
commit 4: feat: implement internship posting
commit 5: feat: add applicant management
commit 6: feat: add shortlist functionality
commit 7: feat: add interview scheduling
```

#### Member 4: Krishna (`feature/admin-portal`)
```
commit 1: feat: create admin dashboard
commit 2: feat: add student management
commit 3: feat: add company verification
commit 4: feat: add job moderation
commit 5: feat: add application monitoring
commit 6: feat: add placement statistics
```

#### Member 5: Himanshu (`feature/database-integration`)
```
commit 1: feat: create database schema
commit 2: feat: implement JDBC connection
commit 3: feat: create DAO layer
commit 4: feat: add application DAO
commit 5: feat: implement notification system
commit 6: test: add authentication tests
commit 7: test: add database integration tests
```

---

### 4. 3-Month Project Timeline Milestones

- **Month 1 (Foundation):**
  - Adhiraj: Project foundation & authentication setup
  - Shlok: Student UI & POJO models
  - Saurabh: Recruiter/Company UI & models
  - Krishna: Admin UI & models
  - Himanshu: MySQL Schema, JDBC connection, and DAO foundations

- **Month 2 (Core Features):**
  - Adhiraj: RBAC security, session tokens, and route protection
  - Shlok: Resume repository, job drives, application submissions
  - Saurabh: Job postings, applicant pipelines, shortlisting
  - Krishna: Admin company verification & user management
  - Himanshu: Application persistence, system integration, notifications

- **Month 3 (Advanced Features & Defense Finalization):**
  - Adhiraj: Final security audit & dashboard access integration
  - Shlok: Application tracking pipeline & UI polish
  - Saurabh: Interview scheduling & candidate workflow
  - Krishna: Interactive charts & placement report generation
  - Himanshu: 25-point automated test suite, zero-setup server runner, documentation
