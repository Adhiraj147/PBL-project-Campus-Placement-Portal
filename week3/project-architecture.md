
---

# 2. `docs/week-03/project-architecture.md`

```markdown
# Campus Placement and Internship Portal
## Week 3 - Project Architecture

## 1. Architecture Overview

The Campus Placement and Internship Portal follows a layered
architecture to separate presentation, request handling, business
operations and database access.

The basic architecture is:

```text
              USER
                |
                v
        JSP / Web Interface
                |
                v
        Servlet Controller
                |
                v
       Application Logic
                |
                v
             DAO
                |
                v
             JDBC
                |
                v
          MySQL Database



          2. Presentation Layer

The presentation layer contains the web interface of the application.

Technologies:

JSP
HTML
CSS
JavaScript

Responsibilities:

Display information.
Accept user input.
Display forms.
Display system responses.
Provide navigation.
3. Controller Layer

The controller layer contains Java Servlets.

Servlets receive requests from the web interface and process them.

Responsibilities include:

Handling HTTP requests.
Validating input.
Calling appropriate DAO methods.
Managing sessions.
Forwarding users to required JSP pages.
4. DAO Layer

The Data Access Object layer handles communication with the database.

Responsibilities:

Execute SQL queries.
Insert records.
Retrieve records.
Update records.
Delete records.
Maintain separation between application logic and database operations.
5. Model Layer

The model layer represents the application's data.

Major model classes include:

User
Student
Company
Job
Application

These classes contain data and provide an object-oriented representation
of database entities.

6. Utility Layer

The utility layer contains common helper classes.

Examples include:

DatabaseConnection
PasswordUtil

These classes provide reusable functionality to different parts of
the application.

7. Database Layer

MySQL is used as the database layer.

The database stores:

User accounts
Student information
Company information
Job and internship information
Application information
8. Request Flow

A typical request follows this flow:

User
 |
 v
JSP Page
 |
 v
Servlet
 |
 v
DAO
 |
 v
JDBC
 |
 v
MySQL
 |
 v
DAO
 |
 v
Servlet
 |
 v
JSP Response
9. Benefits of the Architecture

The layered architecture provides:

Better code organization.
Easier maintenance.
Separation of responsibilities.
Reusable database operations.
Easier debugging.
Easier future expansion.
Better scalability of the application.