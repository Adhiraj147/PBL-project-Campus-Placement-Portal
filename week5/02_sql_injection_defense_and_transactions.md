# SQL Injection Defense, Parameterized Statements & Transaction Isolation
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 5 Technical Specification

---

## 1. SQL Injection Vulnerability Analysis

SQL Injection (SQLi) represents one of the most critical threats to enterprise web applications (OWASP Top 10 A03:2021). An adversary injecting malicious SQL fragments into input fields can bypass authentication, exfiltrate confidential candidate resumes, or wipe entire database tables.

### Vulnerable Dynamic Concatenation Pattern (Anti-Pattern):
```java
// CRITICAL VULNERABILITY: Do NOT use this!
String query = "SELECT * FROM users WHERE email = '" + userInputEmail + "' AND password_hash = '" + hash + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(query);
```
If `userInputEmail` contains `' OR '1'='1' -- `, the authentication check is bypassed completely.

---

## 2. Parameterized PreparedStatement Architecture

In Module 5, **dynamic string concatenation in SQL execution is strictly prohibited**. 100% of data queries and mutations utilize parameterized `PreparedStatement` instances:

```java
String sql = "SELECT user_id, email, password_hash, role, status FROM users WHERE email = ?";
try (PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setString(1, email);
    try (ResultSet rs = stmt.executeQuery()) {
        // Safe evaluation
    }
}
```

### How PreparedStatement Neutralizes Injection:
1. **Pre-Compilation**: The database engine compiles and parses the abstract syntax tree (AST) of the query *before* user parameters are bound.
2. **Type-Safe Bound Literals**: Parameters are transferred over the wire protocol as literal values, never as executable code fragments. Characters like `'`, `;`, and `--` are treated purely as string literals.

---

## 3. Transaction Isolation Levels in MySQL InnoDB

The default transaction isolation level in MySQL InnoDB is **REPEATABLE READ**.

| Isolation Level | Dirty Reads Prevented | Non-Repeatable Reads Prevented | Phantom Reads Prevented | Performance Cost |
| :--- | :---: | :---: | :---: | :---: |
| READ UNCOMMITTED | No | No | No | Lowest |
| READ COMMITTED | Yes | No | No | Low |
| **REPEATABLE READ (Default)** | **Yes** | **Yes** | **Yes (via Next-Key Locks)** | **Balanced** |
| SERIALIZABLE | Yes | Yes | Yes | High |

### Operational Application:
For the user registration transactions in `UserDAOImpl`, `REPEATABLE READ` ensures that when inserting a student record with roll number verification, no concurrent session can insert the same roll number between the existence check and insertion, preserving uniqueness without deadlocks.
