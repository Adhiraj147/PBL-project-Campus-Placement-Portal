# User Authentication Architecture & RBAC Persistence
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Authentication Layer Design

In Week 4, I engineered the User Authentication and Role-Based Access Control (RBAC) persistence tier (`UserDAO` and `UserDAOImpl`). This subsystem handles identity verification for Students, Recruiters, and TPO Administrators.

```mermaid
flowchart TD
    LoginReq[Client POST /login] --> Servlet[LoginServlet - Adhiraj]
    Servlet --> UserDAO[UserDAO.getUserByEmail]
    UserDAO --> DB[(MySQL `users` Table)]
    DB --> UserDAO
    UserDAO --> Servlet
    Servlet --> BCrypt{BCrypt.checkpw}
    BCrypt -- Valid --> Session[Create Session & Route by Role]
    BCrypt -- Invalid --> Reject[Return 401 Unauthorized]
```

---

## 2. Key Responsibilities in `UserDAOImpl`

1. **Identity Retrieval (`getUserByEmail`)**: Fetches credentials, account status (`ACTIVE`, `PENDING`), and role enumeration (`STUDENT`, `RECRUITER`, `ADMIN`).
2. **Atomic Multi-Table Registration**: Ensures student/company specialization rows are created simultaneously with the base `users` record.
3. **Defense-in-Depth Cryptography**: Ensures plain passwords never reach the database; passwords are encrypted with one-way BCrypt salts.
