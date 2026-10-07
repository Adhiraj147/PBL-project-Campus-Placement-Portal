# Authentication Module - Test Results

## Test Environment

- Java
- JSP
- Servlets
- MySQL
- JDBC
- Apache Tomcat
- Maven

## Test Cases

| Test Case | Input | Expected Result | Result |
|---|---|---|---|
| Student Registration | Valid student details | Account created | PASS |
| Recruiter Registration | Valid recruiter details | Account created | PASS |
| Duplicate Email | Existing email | Error displayed | PASS |
| Empty Fields | Missing information | Validation error | PASS |
| Valid Login | Correct credentials | Login successful | PASS |
| Invalid Password | Wrong password | Error displayed | PASS |
| Unknown Email | Unregistered email | Error displayed | PASS |
| Student Login | Student credentials | Student dashboard | PASS |
| Logout | Logged-in user | Session terminated | PASS |

## Conclusion

The authentication module was tested using valid and invalid input
scenarios. Registration, login, validation, role identification and
session-related functionality were verified.