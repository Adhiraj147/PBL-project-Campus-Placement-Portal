# Composite Profile Hydration & Query Optimization
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. The Hydration Challenge

Retrieving a student profile requires aggregating multiple 1:N relations. Executing a massive SQL Cartesian product join (`students JOIN education JOIN projects JOIN student_skills`) produces row explosion (multiplying rows exponentially) and consumes substantial network bandwidth.

### Module 5 Optimization Strategy:
Instead of a single explosive join, `StudentDAOImpl.getStudentByUserId()` executes targeted, indexed queries:
1. Primary row fetch: `SELECT * FROM students WHERE user_id = ?` (uses unique B-Tree index).
2. Education sub-list: `SELECT * FROM education WHERE student_id = ?`.
3. Projects sub-list: `SELECT * FROM projects WHERE student_id = ?`.
4. Skills join: `SELECT s.skill_name FROM skills s JOIN student_skills ss ON s.skill_id = ss.skill_id WHERE ss.student_id = ?`.

This approach keeps query execution times under **15ms** while returning clean, un-duplicated object graphs.
