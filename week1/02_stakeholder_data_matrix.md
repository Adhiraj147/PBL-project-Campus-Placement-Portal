# Stakeholder Data Requirements & System Scope Matrix
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Stakeholder Access Control Matrix

To ensure clear operational boundaries across the three core user personas, the database design enforces access partitions:

| System Domain | Student Access | Recruiter Access | TPO Admin Access |
| :--- | :--- | :--- | :--- |
| **User Authentication (`users`)** | Self Read / Update | Self Read / Update | Full Audit / Status Control |
| **Student Profiles (`students`)** | Own Profile CRUD | Read-Only (Applied Candidates) | Read / Moderation |
| **Education & Projects (`education`, `projects`)** | Own Profile CRUD | Read-Only (Applied Candidates) | Read-Only |
| **Company Profiles (`companies`)** | Read-Only (Directory) | Own Company CRUD | Verification / Moderation |
| **Campus Drives (`opportunities`)** | Read-Only (Open Drives) | Own Drive CRUD | Full Audit / Drive Moderation |
| **Applications (`applications`)** | Submit / Track Own | Screen / Status Update | Global Pipeline Visibility |
| **Audit History (`application_status_history`)** | View Own Timeline | Append on Status Update | Full System Audit Trail |
| **Interviews (`interviews`)** | View Scheduled Slots | Schedule / Modify Slots | Audit & Oversee Scheduling |
| **Notifications (`notifications`)** | Receive Own Alerts | Receive Own Alerts | Broadcast & System Alerts |

---

## 2. Data Volume and Concurrency Projections

For a university cohort of ~5,000 active students and ~150 annual recruiting partners:

| Entity Table | Estimated Annual Rows | Read:Write Ratio | Peak Concurrency Spike |
| :--- | :--- | :---: | :--- |
| `users` | ~6,000 | 10:1 | 500 concurrent logins |
| `students` | ~5,000 | 8:1 | Profile freeze deadlines |
| `opportunities` | ~500 | 50:1 | Drive announcements |
| `applications` | ~60,000 | 5:1 | **1,200 requests/minute** upon drive opening |
| `application_status_history`| ~180,000 | 2:1 | High-volume batch shortlisting |
| `interviews` | ~15,000 | 4:1 | Drive interview slots |
