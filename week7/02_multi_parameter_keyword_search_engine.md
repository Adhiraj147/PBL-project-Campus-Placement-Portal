# Dynamic Multi-Parameter Search Engine Architecture
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 7 Technical Specification

---

## 1. Search Problem Formulation

Candidate students require real-time search capabilities across hundreds of concurrent job and internship postings. Search requirements demand:
1. **Keyword Discovery**: Matching candidate terms against job title (`title`), corporate employer (`company_name`), or required skill tags (`required_skills`).
2. **Employment Type Filtering**: Filtering by `JOB`, `INTERNSHIP`, or `ALL`.
3. **Availability Filter**: Restricting results strictly to active opportunities (`status = 'OPEN'`).
4. **Chronological Sorting**: Presenting freshest opportunities first (`ORDER BY o.created_at DESC`).

---

## 2. Dynamic Query Construction with Bound Parameters

Building search queries dynamically often risks SQL injection if done via string interpolation. In `OpportunityDAOImpl.searchOpportunities()`, dynamic query assembly is executed using `StringBuilder` while strictly parameterizing each value placeholder:

```java
StringBuilder sql = new StringBuilder(
    "SELECT o.*, c.company_name FROM opportunities o " +
    "JOIN companies c ON o.company_id = c.company_id " +
    "WHERE o.status = 'OPEN'"
);

if (keyword != null && !keyword.trim().isEmpty()) {
    sql.append(" AND (o.title LIKE ? OR c.company_name LIKE ? OR o.required_skills LIKE ?)");
}
if (type != null && !type.isEmpty() && !type.equals("ALL")) {
    sql.append(" AND o.type = ?");
}

sql.append(" ORDER BY o.created_at DESC");
```

### Parameter Binding Execution:
```java
int paramIndex = 1;
if (keyword != null && !keyword.trim().isEmpty()) {
    String searchPattern = "%" + keyword.trim() + "%";
    stmt.setString(paramIndex++, searchPattern);
    stmt.setString(paramIndex++, searchPattern);
    stmt.setString(paramIndex++, searchPattern);
}
if (type != null && !type.isEmpty() && !type.equals("ALL")) {
    stmt.setString(paramIndex, type);
}
```

### Performance & Security Metrics:
- **Zero Concatenation**: Injection payloads like `' OR '1'='1` in the search box are safely treated as verbatim substring patterns (`% ' OR '1'='1 %`).
- **Index Utilization**: Composite index `idx_opp_status_deadline (status, deadline)` allows MySQL query optimizer to perform fast index range scans before evaluating substring filters.
