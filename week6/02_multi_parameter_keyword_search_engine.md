# Multi-Parameter Keyword Search Engine & SQL Indexing
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Dynamic Search Architecture

Students frequently search across hundreds of job openings using diverse criteria (e.g. searching for `"Java"`, `"Google"`, or filtering by `type='INTERNSHIP'`).

In `OpportunityDAOImpl.searchOpportunities()`, I built a dynamic SQL query generator using `StringBuilder` and parameterized `PreparedStatement`:

```java
StringBuilder sql = new StringBuilder(
    "SELECT o.*, c.company_name FROM opportunities o " +
    "JOIN companies c ON o.company_id = c.company_id " +
    "WHERE o.status = 'OPEN' "
);
if (hasKeyword) {
    sql.append("AND (o.title LIKE ? OR c.company_name LIKE ? OR o.required_skills LIKE ?) ");
}
if (hasType) {
    sql.append("AND o.type = ? ");
}
sql.append("ORDER BY o.created_at DESC");
```

### Performance & Safety:
- **Wildcard Injection Prevention**: Parameter substitution using `stmt.setString(paramIdx++, "%" + keyword + "%")` neutralizes SQL meta-characters.
- **Join Optimization**: The join between `opportunities` and `companies` leverages the foreign key index on `company_id`.
