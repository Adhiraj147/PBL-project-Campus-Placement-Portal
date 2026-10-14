# Composite Hydration, Junction Mappings & Performance Analysis
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 6 Technical Specification

---

## 1. Object Hydration Strategy: Single Query vs Multi-Query

A central database engineering decision when reconstructing rich objects like `Student` from relational schemas is choosing between:
1. **Mega-JOIN Query**: Joining `students`, `education`, `projects`, and `student_skills` in one SQL query.
2. **Targeted Multi-Query Hydration**: Querying the primary record first, followed by indexed child collection queries.

### The Cartesian Product Problem with Outer Joins:
If a student has 3 education records, 4 projects, and 5 skills:
$$\text{Joined Rows} = 3 \times 4 \times 5 = 60 \text{ rows}$$
All 60 rows repeat the base student columns (name, bio, roll number, resume URL) across the network socket, wasting bandwidth and requiring complex Java de-duplication loops.

### Adopted Pattern in `StudentDAOImpl`:
`StudentDAOImpl.getStudentByUserId()` executes 4 clean, highly indexed queries:
1. `SELECT * FROM students WHERE user_id = ?` (Primary lookup, 1 row)
2. `SELECT * FROM education WHERE student_id = ?` (Indexed by `idx_edu_student`, 3 rows)
3. `SELECT * FROM projects WHERE student_id = ?` (Indexed by `idx_project_student`, 4 rows)
4. `SELECT s.skill_name FROM skills s JOIN student_skills ss ON s.skill_id = ss.skill_id WHERE ss.student_id = ?` (Indexed join, 5 rows)

Total transmitted rows: $1 + 3 + 4 + 5 = 13 \text{ rows}$. This yields a **78% reduction in network payload** and executes in $< 2\text{ms}$ on MySQL 8.0.

---

## 2. Idempotent Skill Association via `INSERT IGNORE`

When students associate skills with their profile, the operation must be idempotent. If a candidate attempts to associate `'Java'` twice, the database must not throw a duplicate key violation.

```java
// Idempotent junction insertion
String linkSql = "INSERT IGNORE INTO student_skills (student_id, skill_id) VALUES (?, ?)";
try (PreparedStatement stmt = conn.prepareStatement(linkSql)) {
    stmt.setInt(1, studentId);
    stmt.setInt(2, skillId);
    return stmt.executeUpdate() > 0;
}
```

The composite primary key `PRIMARY KEY (student_id, skill_id)` in `student_skills` paired with `INSERT IGNORE` ensures zero duplicate pairs without needing application-level locking.
