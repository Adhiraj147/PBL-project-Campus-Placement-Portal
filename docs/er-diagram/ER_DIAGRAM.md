# Entity-Relationship (ER) Documentation
## Campus Placement and Internship Portal Database

---

### 1. Conceptual Entity-Relationship Diagram

```mermaid
erDiagram
    USERS ||--o| STUDENT_PROFILES : "has profile (1:1)"
    USERS ||--o| COMPANY_PROFILES : "has profile (1:1)"
    USERS ||--o{ NOTIFICATIONS : "receives (1:N)"
    
    COMPANY_PROFILES ||--o{ JOBS : "publishes (1:N)"
    
    STUDENT_PROFILES ||--o{ APPLICATIONS : "submits (1:N)"
    JOBS ||--o{ APPLICATIONS : "receives (1:N)"
    
    APPLICATIONS ||--o{ INTERVIEWS : "schedules (1:N)"

    USERS {
        int id PK
        string email UK
        string password_hash
        enum role "STUDENT, RECRUITER, ADMIN"
        string full_name
        string phone
        enum status "ACTIVE, PENDING, SUSPENDED"
        timestamp created_at
    }

    STUDENT_PROFILES {
        int id PK
        int user_id FK
        string roll_number UK
        string branch
        decimal cgpa
        int graduation_year
        string resume_url
        text skills
        text bio
        string linkedin_url
        string github_url
    }

    COMPANY_PROFILES {
        int id PK
        int user_id FK
        string company_name
        string industry
        string website
        string location
        text description
        enum verification_status "PENDING, VERIFIED, REJECTED"
        timestamp verified_at
    }

    JOBS {
        int id PK
        int company_id FK
        string title
        text description
        enum job_type "FULL_TIME, INTERNSHIP"
        decimal package_lpa
        decimal stipend_pm
        string location
        decimal min_cgpa
        string eligible_branches
        date deadline
        enum status "PENDING_APPROVAL, ACTIVE, CLOSED"
        timestamp created_at
    }

    APPLICATIONS {
        int id PK
        int job_id FK
        int student_id FK
        enum status "APPLIED, SHORTLISTED, INTERVIEW_SCHEDULED, SELECTED, REJECTED"
        text cover_note
        timestamp applied_at
        timestamp updated_at
    }

    INTERVIEWS {
        int id PK
        int application_id FK
        string round_name
        datetime scheduled_time
        string meeting_link
        enum mode "ONLINE, IN_PERSON"
        enum status "SCHEDULED, COMPLETED, CANCELLED"
        text feedback
        timestamp created_at
    }

    NOTIFICATIONS {
        int id PK
        int user_id FK
        string title
        text message
        boolean is_read
        timestamp created_at
    }
```

---

### 2. Relational Integrity Rules & Constraints

1. **Foreign Key Cascades:**
   - Deleting a `USER` automatically cascades to delete their associated `STUDENT_PROFILE` or `COMPANY_PROFILE`, along with their `NOTIFICATIONS`.
   - Deleting a `COMPANY_PROFILE` removes all associated `JOBS`, which cascades to remove all associated `APPLICATIONS` and `INTERVIEWS`.
2. **Duplicate Application Prevention:**
   - A unique compound key `uk_student_job (student_id, job_id)` prevents a candidate from submitting duplicate applications for the same campus drive.
3. **Auditability & Timestamps:**
   - `applications` tracks both initial `applied_at` and live `updated_at` timestamps to maintain transparency across recruiter status transitions.
