
---

# 2. `docs/week-04/authentication-flow.md`

```markdown
# Authentication Flow

## 1. Registration Flow

```text
User
 |
 v
Registration Page
 |
 v
Enter User Information
 |
 v
Validate Input
 |
 v
Check Existing Email
 |
 +---- Email Exists ----> Show Error
 |
 v
Hash Password
 |
 v
Create User Account
 |
 v
Create Role-Specific Profile
 |
 v
Registration Successful
 |
 v
Login Page


User
 |
 v
Login Page
 |
 v
Enter Email + Password
 |
 v
Find User in Database
 |
 +---- User Not Found ----> Show Error
 |
 v
Verify Password
 |
 +---- Invalid Password ----> Show Error
 |
 v
Check Account Status
 |
 v
Create Session
 |
 v
Identify User Role
 |
 +----------+----------+----------+
 |          |          |
Student   Recruiter   Admin
 |          |          |
 v          v          v
Student   Recruiter   Admin
Dashboard Dashboard   Dashboard



Logged-in User
      |
      v
Click Logout
      |
      v
Invalidate Session
      |
      v
Return to Login Page




             JSP Login/Register
                     |
                     v
              Java Servlet
                     |
                     v
                 UserDAO
                     |
                     v
             UserDAOImpl
                     |
                     v
                  JDBC
                     |
                     v
              MySQL Database




              