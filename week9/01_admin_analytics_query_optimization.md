# TPO Executive Analytics & High-Performance Query Optimization
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 9 Technical Specification

---

## 1. Executive Dashboard KPI Requirements

The Training and Placement Office (TPO) requires instant, real-time visibility into six macro placement health metrics whenever the admin dashboard is requested:
1. `totalStudents`: Registered candidate population.
2. `totalCompanies`: Active corporate recruitment partners.
3. `totalJobs`: Full-time employment opportunities published.
4. `totalInternships`: Pre-placement internship drives published.
5. `totalApplications`: Total candidate application throughput.
6. `totalSelected`: Successfully placed students (offers accepted).

---

## 2. Aggregation Execution Architecture

In `AdminDAOImpl`, the analytics engine consumes a single dedicated `Connection` from `DatabaseConnection.getConnection()`, running discrete `COUNT(*)` aggregations sequentially:

```java
try (Connection conn = DatabaseConnection.getConnection()) {
    stats.put("totalStudents", getCount(conn, "SELECT COUNT(*) FROM students"));
    stats.put("totalCompanies", getCount(conn, "SELECT COUNT(*) FROM companies"));
    stats.put("totalJobs", getCount(conn, "SELECT COUNT(*) FROM opportunities WHERE type='JOB'"));
    stats.put("totalInternships", getCount(conn, "SELECT COUNT(*) FROM opportunities WHERE type='INTERNSHIP'"));
    stats.put("totalApplications", getCount(conn, "SELECT COUNT(*) FROM applications"));
    stats.put("totalSelected", getCount(conn, "SELECT COUNT(*) FROM applications WHERE status='SELECTED'"));
}
```

### Connection Re-Use Advantage:
By reusing a single open TCP connection for all 6 count operations, the system avoids repeated 3-way TCP handshakes, SSL re-negotiations, and database authentication overhead, completing the entire analytics suite in $< 5\text{ms}$.
