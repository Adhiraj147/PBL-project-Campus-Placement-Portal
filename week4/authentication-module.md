# Campus Placement and Internship Portal
## Week 4 - Authentication and User Management

## 1. Introduction

During Week 4, the authentication and user management module of the
Campus Placement and Internship Portal was implemented.

This module provides registration and login functionality and manages
user access according to their role.

The authentication system forms the foundation for protecting the
different modules of the portal.

---

## 2. Objectives

The main objectives of this module are:

- Provide user registration.
- Provide secure login.
- Store user account information in MySQL.
- Validate user credentials.
- Implement password hashing.
- Implement role-based authentication.
- Manage user sessions.
- Prevent unauthorized access.
- Handle invalid login and registration attempts.

---

## 3. User Roles

The system supports different user roles:

### Student

Students can register and access student-related functionality.

### Recruiter

Recruiters can register and access company and recruitment-related
functionality.

### Administrator

Administrators can access administrative functionality.

---

## 4. Registration Module

The registration module allows new users to create an account.

The registration process includes:

1. User opens the registration page.
2. User selects the required role.
3. User enters the required information.
4. Input data is validated.
5. The system checks whether the email is already registered.
6. Password is processed securely.
7. User information is stored in the database.
8. Role-specific profile information is created.
9. User is redirected to the login page after successful registration.

---

## 5. Student Registration

For student registration, the system collects information such as:

- Email
- Password
- First name
- Last name
- Roll number

The student account is associated with a student profile in the
database.

---

## 6. Recruiter Registration

For recruiter registration, the system collects:

- Email
- Password
- Company name

A company profile is created and associated with the recruiter account.

---

## 7. Login Module

The login module verifies registered users before allowing access to
protected areas of the application.

The login process includes:

1. User enters email.
2. User enters password.
3. System searches for the account.
4. Password is verified.
5. Account status is checked.
6. User role is identified.
7. A session is created.
8. User is redirected to the appropriate dashboard.

---

## 8. Password Security

Passwords are not stored directly as plain text.

A password hashing mechanism is used before storing the password in the
database.

The basic process is:

```text
User Password
      |
      v
Password Hashing
      |
      v
Hashed Password
      |
      v
MySQL Database




                    Login
                      |
                      v
                 Authenticate
                      |
          +-----------+-----------+
          |           |           |
       Student     Recruiter     Admin
          |           |           |
          v           v           v
      Student      Recruiter    Admin
     Dashboard     Dashboard   Dashboard