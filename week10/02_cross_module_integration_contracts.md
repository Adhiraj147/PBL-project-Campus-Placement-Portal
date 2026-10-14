# Cross-Module Integration Contracts & Service Boundaries
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 10 Technical Specification

---

## 1. System Integration Map

Module 5 provides the foundational database engine and business service interfaces consumed by all upstream feature modules:

| Consumer Module | Owner | Consumed Module 5 Components | Integration Contract |
| :--- | :--- | :--- | :--- |
| **Module 1: Authentication & RBAC** | Adhiraj | `UserDAO`, `DatabaseConnection` | Registers credentials, verifies login tokens, creates base profile records. |
| **Module 2: Student Candidate Portal** | Shlok | `StudentDAO`, `OpportunityDAO`, `ApplicationDAO`, `EligibilityService` | Fetches portfolio, runs eligibility checks, submits job applications. |
| **Module 3: Recruiter Portal** | Saurabh | `CompanyDAO`, `OpportunityDAO`, `ApplicationDAO` | Authors placement drives, manages candidate pipelines, mutates status with audit logging. |
| **Module 4: TPO Administration** | Krishna | `AdminDAO`, `ApplicationDAO`, `OpportunityDAO` | Generates institutional KPI summaries, enforces global audit oversight. |

---

## 2. API Contract Example: Candidate Application Flow

When Shlok's `StudentApplicationServlet` handles an incoming apply request:

```java
// 1. Hydrate candidate profile from Module 5 DAO
Student student = studentDAO.getStudentByUserId(currentUserId);

// 2. Hydrate target opportunity
Opportunity opp = opportunityDAO.getOpportunityById(oppId);

// 3. Execute Module 5 Eligibility Gatekeeper
EligibilityService.EligibilityResult result = EligibilityService.checkEligibility(student, opp);

if (!result.isEligible()) {
    // Return structured rejection feedback
    request.setAttribute("errorMessage", result.getReason());
    request.getRequestDispatcher("/views/student/opportunity-detail.jsp").forward(request, response);
    return;
}

// 4. Persist application submission atomically
boolean applied = applicationDAO.apply(student.getStudentId(), opp.getOppId());
```

This strict separation of concerns ensures zero leaky database abstractions into presentation servlets.
