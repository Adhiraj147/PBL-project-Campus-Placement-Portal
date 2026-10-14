# Architecture Overview
## Campus Placement and Internship Portal

### 1. Introduction

The Campus Placement and Internship Portal follows a layered
Java web application architecture.

The architecture separates the user interface, request handling,
data access, and database operations.

### 2. Architecture Layers

#### A. Presentation Layer

Technologies:
- JSP
- HTML
- CSS
- JavaScript

Responsibilities:
- Display application pages.
- Collect user input.
- Display application results.
- Show appropriate messages.

#### B. Controller Layer

Technology:
- Java Servlets

Responsibilities:
- Receive HTTP requests.
- Process form submissions.
- Validate request parameters.
- Coordinate application workflows.
- Forward or redirect requests.

#### C. Data Access Layer

Technologies:
- Java DAO classes
- JDBC

Responsibilities:
- Execute database queries.
- Insert records.
- Retrieve records.
- Update existing records.
- Handle database-related exceptions.

#### D. Model Layer

Technology:
- Java classes

Responsibilities:
- Represent application data.
- Transfer data between application layers.
- Organize information related to users, students,
  recruiters, jobs, and applications where implemented.

#### E. Database Layer

Technology:
- MySQL

Responsibilities:
- Store application data.
- Maintain relationships between records.
- Support retrieval and modification of data.

### 3. Request Flow

1. A user opens a page in the browser.
2. The user submits a form or requests a resource.
3. A Servlet receives the HTTP request.
4. The controller validates and processes the request.
5. A DAO communicates with MySQL through JDBC when required.
6. The result is returned to the controller.
7. The controller forwards the result to a JSP or redirects
   the browser to another page.

### 4. Main User Roles

#### Student

The student workflow may include registration, login, viewing
available jobs, and applying for jobs, depending on the
implemented features.

#### Recruiter

The recruiter workflow may include account registration,
viewing recruiter information, managing job postings, and
reviewing applicants, depending on the implemented features.

#### Administrator

The administrator workflow may include reviewing users and
managing access, depending on the implemented features.

### 5. Database Integration

The application uses JDBC to communicate with MySQL.

DAO classes help separate SQL operations from Servlet
request-handling logic.

The exact tables, columns, and relationships are defined
by the project's actual database schema.

### 6. Benefits of the Architecture

- Separation of responsibilities.
- Easier debugging and maintenance.
- Reusable database access methods.
- Better organization of Java classes.
- Easier extension of existing functionality.

### 7. Conclusion

The layered architecture provides an organized foundation
for developing and maintaining the Campus Placement and
Internship Portal.