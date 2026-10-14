# Campus Placement and Internship Portal
## Week 3 - Development Environment Setup

## 1. Introduction

During Week 3, the development environment for the Campus Placement
and Internship Portal was configured.

The required Java web development tools, project structure, build
configuration, application server and database connectivity were
prepared for further development.

---

## 2. Development Technologies

The following technologies were configured for the project:

- Java
- JSP
- Servlets
- Maven
- MySQL
- JDBC
- Apache Tomcat
- HTML
- CSS
- JavaScript
- Git and GitHub

---

## 3. Java Environment

Java was configured as the primary programming language for the
backend development.

Java is used for:

- Servlet development
- Backend business logic
- Database interaction
- Application processing
- User authentication
- Data management

---

## 4. Maven Configuration

Maven was used as the project build and dependency management tool.

Maven helps manage:

- Project dependencies
- Build configuration
- Project structure
- Packaging
- Application deployment

The project contains a `pom.xml` file for Maven configuration.

---

## 5. Apache Tomcat Configuration

Apache Tomcat was configured as the web application server.

Tomcat is responsible for running:

- Java Servlets
- JSP pages
- Web application requests
- Server-side Java components

The application was configured to run through the Tomcat server.

---

## 6. JDBC Configuration

JDBC was selected for communication between the Java application and
the MySQL database.

The basic communication flow is:

```text
Java Application
       |
       v
      JDBC
       |
       v
MySQL Database


JDBC allows the application to:

Establish database connections.
Execute SQL queries.
Insert records.
Retrieve records.
Update records.
Delete records.
7. Database Connection

A centralized database connection approach was planned for the
application.

The database connection layer is responsible for:

Loading database configuration.
Establishing a connection.
Providing connections to DAO classes.
Handling database connection errors.
8. Project Structure

The Java web application was organized into separate packages and
directories.

The planned structure is:

Campus Placement Portal
│
├── src
│   └── main
│       ├── java
│       │   └── com.placement
│       │       ├── controller
│       │       ├── dao
│       │       ├── model
│       │       └── util
│       │
│       └── webapp
│           ├── css
│           ├── js
│           ├── student
│           ├── recruiter
│           ├── admin
│           └── JSP pages
│
├── pom.xml
└── README.md
9. Backend Package Structure
Controller

Contains Servlets responsible for handling HTTP requests.

Examples:

LoginServlet
RegisterServlet
StudentDashboardServlet
DAO

Contains database access classes.

DAO classes are responsible for executing database operations.

Model

Contains Java classes representing application entities.

Examples:

User
Student
Company
Job
Application
Util

Contains utility classes used throughout the application.

Examples:

DatabaseConnection
PasswordUtil
10. Initial Application Testing

After configuring the development environment, the application was
tested to verify that:

The Java environment was working.
Maven project configuration was working.
Tomcat could run the web application.
JSP pages could be served.
Servlet requests could be processed.
MySQL connectivity could be established.
11. Week 3 Completion Status

The following activities were completed:

Java web development environment configured.
Maven project configuration prepared.
Apache Tomcat configured.
JDBC connectivity prepared.
MySQL connection tested.
Backend package structure planned.
Web application structure prepared.
Initial application execution tested.