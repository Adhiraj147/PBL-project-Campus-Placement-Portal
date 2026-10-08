# Week 2 – Recruiter Registration Validation and Test Plan

## Student
Saurabh Gupta

## Project
Campus Placement and Internship Portal

## Module
Recruiter & Company Portal

## GitHub Branch
feature/saurabh

---

## 1. Objective

The objective of this document is to define the validation rules and test cases for the Recruiter Registration feature.

Validation is required to prevent incomplete, invalid or duplicate recruiter accounts from being created.

---

## 2. Email Validation

Email is an important field because it is used to identify the recruiter account.

### Validation Rules

- Email should not be empty.
- Email should use a valid email format.
- Leading and trailing spaces should not affect the email.
- The same email should not be registered multiple times.

### Example Valid Input

recruiter@company.com

### Example Invalid Inputs

recruiter

recruiter@

@company.com

---

## 3. Password Validation

The password is required for authentication.

### Validation Rules

- Password should not be empty.
- Password should satisfy the minimum required length.
- Password should be processed securely before storage.

The password should not be stored as plain readable text.

---

## 4. Role Validation

The account role determines which portal functionality the user can access.

For this feature, the expected role is:

RECRUITER

The registration process should ensure that the selected role is valid before creating the account.

---

## 5. Company Name Validation

A recruiter represents a company, therefore company information is required during recruiter registration.

### Validation Rules

- Company name should not be empty for a recruiter.
- Unnecessary spaces should be removed.
- The company information should be associated with the recruiter account.

---

## 6. Duplicate Account Validation

Before account creation, the system should check whether the entered email is already associated with an existing account.

If the email already exists, registration should stop and an appropriate message should be displayed.

---

## 7. Registration Test Cases

### Test Case 1 – Valid Recruiter Registration

**Input:**

Email: recruiter@company.com

Password: valid password

Role: RECRUITER

Company Name: Example Technologies

**Expected Result:**

Recruiter account should be created successfully.

---

### Test Case 2 – Empty Email

**Input:**

Email: Empty

Password: valid password

Role: RECRUITER

Company Name: Example Technologies

**Expected Result:**

Registration should be rejected because email is required.

---

### Test Case 3 – Invalid Email

**Input:**

Email: recruiter

Password: valid password

Role: RECRUITER

Company Name: Example Technologies

**Expected Result:**

Registration should be rejected because the email format is invalid.

---

### Test Case 4 – Empty Password

**Input:**

Email: recruiter@company.com

Password: Empty

Role: RECRUITER

Company Name: Example Technologies

**Expected Result:**

Registration should be rejected because password is required.

---

### Test Case 5 – Missing Company Name

**Input:**

Email: recruiter@company.com

Password: valid password

Role: RECRUITER

Company Name: Empty

**Expected Result:**

Registration should be rejected because company name is required for a recruiter.

---

### Test Case 6 – Duplicate Email

**Input:**

Email: Email already registered in the system

Password: valid password

Role: RECRUITER

Company Name: Example Technologies

**Expected Result:**

Registration should be rejected because an account already exists with the entered email.

---

### Test Case 7 – Valid Company Association

**Input:**

Valid recruiter registration information

**Expected Result:**

After account creation, the company profile should be associated with the recruiter account.

---

## 8. Error Handling Plan

The registration system should provide understandable messages for validation failures.

Examples:

- Please enter an email address.
- Please enter a valid email address.
- Password is required.
- Company name is required for recruiter registration.
- Email is already registered.
- Registration failed. Please try again.

---

## 9. Security Considerations

The recruiter registration process should consider the following security requirements:

- Passwords should not be stored in plain text.
- User input should be validated.
- Duplicate accounts should be prevented.
- Recruiter-specific access should depend on the user's role.
- Invalid registration information should not be stored.

---

## 10. Week 2 Work Completed

During Week 2:

- Recruiter registration requirements were studied.
- Required recruiter information was identified.
- Recruiter registration workflow was designed.
- Company association workflow was planned.
- Validation rules were documented.
- Duplicate-account handling was considered.
- Registration test cases were prepared.
- Error handling requirements were documented.

---

## 11. Week 2 Outcome

The Recruiter Registration feature has been fully planned from requirements through validation and testing.

The resulting documentation provides the functional foundation required for recruiter account creation and prepares the recruiter module for the Recruiter Dashboard feature.
