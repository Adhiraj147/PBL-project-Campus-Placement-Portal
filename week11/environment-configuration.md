# Environment Configuration

## 1. Purpose

This document describes the environment configuration required
to run the Campus Placement and Internship Portal.

## 2. Development Environment

The development environment includes:

- Java Development Kit
- Apache Maven
- Apache Tomcat
- MySQL Server
- MySQL Workbench
- Visual Studio Code or the configured development IDE
- Git

## 3. Application Configuration

The application requires the following configuration:

- Database URL
- Database name
- Database username
- Database password
- JDBC driver
- Servlet container configuration

The actual values depend on the environment in which
the application is running.

## 4. Database Configuration

The application connects to MySQL using JDBC.

The database connection configuration must point to the
correct database server and database name.

The database schema should be initialized before the
application is tested in a new environment.

## 5. Configuration Separation

Development and production environments may use different
database servers and credentials.

Environment-specific values should be stored separately
from public source code whenever possible.

## 6. Credential Security

Database credentials must not be committed to GitHub.

Production credentials should be stored in the hosting
platform's environment or secret configuration.

If credentials have already been exposed publicly, rotate them.

## 7. Runtime Compatibility

The Java version, Tomcat version, Servlet API, and application
dependencies must be compatible.

The existing project configuration should be checked before
upgrading any component.

## 8. Configuration Verification

Before deployment, verify:

- Database connection succeeds.
- Required tables exist.
- The application builds successfully.
- The WAR file is generated.
- The application starts without configuration errors.
- Login and database-dependent features work.

## 9. Conclusion

Correct environment configuration helps ensure consistent
application behavior across development and deployment environments.