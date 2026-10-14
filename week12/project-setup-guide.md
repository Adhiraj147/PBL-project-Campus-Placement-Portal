# Project Setup Guide
## Campus Placement and Internship Portal

### 1. Project Overview

The Campus Placement and Internship Portal is a Java-based web
application designed to support campus recruitment activities.

It provides functionality for the user roles and workflows
implemented in the project.

The application uses Java web technologies and MySQL for
database operations.

### 2. Technologies Required

- Java Development Kit (JDK)
- Apache Maven
- Apache Tomcat compatible with the project
- MySQL Server
- MySQL Workbench (optional)
- Visual Studio Code or another compatible IDE
- A modern web browser

### 3. Prerequisites

Before running the application, ensure that:

1. Java is installed.
2. Maven is installed and configured.
3. MySQL Server is running.
4. The project source code is available locally.
5. The database has been created.
6. Required database tables have been created.
7. Database configuration matches the local environment.

### 4. Database Configuration

The project uses the MySQL database configured for the application.

Database name:

campus_placement_db

The database schema is available in the project's SQL directory,
subject to the actual project structure.

Before importing or executing the schema, inspect the existing
database to avoid overwriting working data.

Configure the database URL, username, and password according
to the project's existing database connection implementation.

Do not commit real database passwords or other secrets to GitHub.

### 5. Running the Project

Open a terminal in the project root directory.

Verify Java:

java -version

Verify Maven:

mvn -version

Start the project using the development command configured
for this project:

mvn tomcat7:run

Wait until the server reports that it has started successfully.

### 6. Opening the Application

Open the local application URL in a browser:

http://localhost:8080/campus-placement-portal

The exact URL depends on the project's context path and server
configuration.

### 7. Building the Application

To create a clean Maven build, run:

mvn clean package

If the build succeeds, inspect the target directory for the
generated WAR file.

The WAR file can be deployed only to a compatible servlet
container.

### 8. Troubleshooting

#### Database connection failure

- Confirm that MySQL is running.
- Verify the database name.
- Check the JDBC URL.
- Check the database username and password.
- Verify that the required JDBC driver is available.

#### Application does not start

- Review the terminal output.
- Check the Maven build result.
- Verify Java and servlet-container compatibility.
- Check for port conflicts.

#### Login or registration failure

- Inspect the application logs.
- Verify the submitted form fields.
- Confirm that the required database records and tables exist.
- Check the relevant Servlet and DAO implementation.

### 9. Security Notes

- Never publish database passwords.
- Do not upload local secret configuration files.
- Restrict administrator functionality to authorized users.
- Validate user input on the server.
- Use HTTPS when deploying the application publicly.
- Configure appropriate session and access controls.

### 10. Conclusion

Following this guide should help a developer configure and run
the project in a compatible local environment.