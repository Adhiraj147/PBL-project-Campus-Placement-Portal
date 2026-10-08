# SQL Injection Defense & Transactional Integrity
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. SQL Injection Vulnerability Analysis

SQL Injection (SQLi) is an attack where malicious SQL fragments are injected into application inputs to compromise database queries.

### Insecure Concatenation Example (Vulnerable):
```java
// CRITICAL VULNERABILITY: Never used in Module 5
String sql = "SELECT * FROM users WHERE email = '" + email + "' AND password = '" + pass + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
```

### Secure Parameterized Query (Enforced Across All DAOs):
```java
// Production standard in UserDAOImpl
String sql = "SELECT * FROM users WHERE email = ?";
try (Connection conn = DatabaseConnection.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, email);
    try (ResultSet rs = stmt.executeQuery()) { ... }
}
```
**Mechanism**: In `PreparedStatement`, the SQL query is pre-compiled by the database engine before parameters are bound. User inputs are treated strictly as literal data values rather than executable code, eliminating SQL injection vectors.

---

## 2. Multi-Table Atomic Transactions

Registration requires creating a base credentials record in `users` followed by a specialized role record in `students` or `companies`.

If the second insertion fails (e.g., due to a duplicate `roll_no` constraint violation), leaving the `users` record in the database creates a corrupted orphaned account.

To enforce the **Atomicity** guarantee of ACID, `UserDAOImpl` implements manual transaction management:

```mermaid
sequenceDiagram
    autonumber
    participant App as RegisterServlet
    participant DAO as UserDAOImpl
    participant DB as MySQL Connection

    App->>DAO: registerStudentWithTransaction(user, first, last, rollNo)
    DAO->>DB: conn.setAutoCommit(false)
    DAO->>DB: INSERT INTO users VALUES (...)
    alt users insert fails
        DAO->>DB: conn.rollback()
        DAO-->>App: Returns -1 (Registration Failed)
    else users insert succeeds
        DB-->>DAO: Generated user_id (e.g., 42)
        DAO->>DB: INSERT INTO students (user_id=42, roll_no=rollNo)
        alt students insert fails
            DAO->>DB: conn.rollback()
            DAO-->>App: Throws SQLException (Rollback Executed)
        else students insert succeeds
            DAO->>DB: conn.commit()
            DAO->>DB: conn.setAutoCommit(true)
            DAO-->>App: Returns new user_id (42)
        end
    end
```
