# Recruiter Company & Opportunity Persistence Architecture
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Corporate Placement Drives Model

The corporate opportunity subsystem connects corporate recruitment partners (`companies`) with published recruitment drives (`opportunities`).

In Week 6, I developed `CompanyDAO`, `CompanyDAOImpl`, `OpportunityDAO`, and `OpportunityDAOImpl` to enable:
1. **Company Profile Management**: Updating sector, corporate overview, location, and HR contact points.
2. **Drive Authoring**: Creating campus job or internship drives with automated eligibility criteria (minimum CGPA cutoffs, batch graduation year, eligible department csv lists, required skills).
3. **Drive Lifecycle Control**: Enabling recruiters and TPO admins to terminate drives (`status = 'CLOSED'`).
