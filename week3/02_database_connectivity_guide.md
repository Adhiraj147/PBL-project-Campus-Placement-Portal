# Database Connectivity & Environment Setup Guide
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Contact: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal

---

## 1. Quick Start Guide for Team Members

This document guides team developers (Adhiraj, Shlok, Saurabh, Krishna) on setting up database connectivity for their local workstations and deployment environments.

`DatabaseConnection.java` supports zero-code configuration via standard system environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`).

---

## 2. Option A: Local MySQL Setup (Recommended for Offline Development)

Ensure MySQL Server 8.0+ is installed and running on port 3306.

### Step 1: Initialize Database and Schema
Open MySQL Command Line or MySQL Workbench and run:
```sql
SOURCE week2/schema.sql;
```
Or execute from terminal:
```bash
mysql -u root -p < week2/schema.sql
```

### Step 2: Configure Environment Variables
Set the following environment variables in your terminal, IDE run configuration, or operating system:

**On Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="your_local_password"
```

**On Linux / macOS (Bash):**
```bash
export DB_URL="jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export DB_USER="root"
export DB_PASSWORD="your_local_password"
```

---

## 3. Option B: Running with Docker Container

A turnkey Docker container can be spun up in seconds:
```bash
docker run --name placement-mysql \
  -e MYSQL_ROOT_PASSWORD=rootpassword \
  -e MYSQL_DATABASE=campus_placement_db \
  -p 3306:3306 \
  -d mysql:8.0
```

To load the schema into the container:
```bash
docker exec -i placement-mysql mysql -uroot -prootpassword campus_placement_db < week2/schema.sql
```

Configure your environment variables:
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="rootpassword"
```

---

## 4. Option C: Managed Cloud Database (Aiven / AWS RDS)

When deploying to cloud application servers (such as Render or AWS ECS):
1. Create a MySQL service in your cloud provider.
2. In the cloud application environment settings, set:
   - `DB_URL`: `jdbc:mysql://<cloud-host>:<port>/<db_name>?sslMode=REQUIRED&serverTimezone=UTC`
   - `DB_USER`: `<cloud_username>`
   - `DB_PASSWORD`: `<cloud_password>`
3. `DatabaseConnection.java` automatically detects these variables and establishes encrypted TLS connections.

---

## 5. Verification Test

To verify connectivity from Java:
```java
boolean isConnected = DatabaseConnection.testConnection();
if (isConnected) {
    System.out.println(">> Database Connection SUCCESSFUL!");
} else {
    System.err.println(">> Database Connection FAILED!");
}
```
A complete 25-point integration test suite is provided in `week7/test/DatabaseIntegrationTest.java`.
