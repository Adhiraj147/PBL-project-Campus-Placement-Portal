# Database Connectivity & Environment Setup Guide
## Module 5: Database Architecture, Integration Layer & System Testing
### Author: Himanshu Yadav ([@XXHimanshuXX](https://github.com/XXHimanshuXX) | [LinkedIn](https://www.linkedin.com/in/himanshu-yadav-112ba6376))
### Email: himanshu.yadav060107@gmail.com
### Project: Campus Placement and Internship Portal
### Milestone: Week 4 Technical Specification

---

## 1. Quick Start Guide for Development Teams

This guide provides instructions for configuring, initializing, and validating the database tier for local development, automated testing, and Docker execution.

---

## 2. Option A: Local Native MySQL 8.0 Setup

### Step 1: Install MySQL Community Server
Ensure MySQL 8.0 or higher is installed and active on the default port `3306`.

```bash
# Verify MySQL service status
mysql --version
systemctl status mysql     # Linux
Get-Service MySQL*         # Windows PowerShell
```

### Step 2: Initialize Database and Execute DDL
Run the Week 3 schema script using the MySQL client:

```bash
mysql -u root -p < week3/schema.sql
```

### Step 3: Configure Local Environment Variables
Set environment variables in your terminal or IDE run configuration:

```powershell
# Windows PowerShell
$env:DB_URL = "jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER = "root"
$env:DB_PASSWORD = "your_local_password"
```

```bash
# macOS / Linux Bash
export DB_URL="jdbc:mysql://localhost:3306/campus_placement_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
export DB_USER="root"
export DB_PASSWORD="your_local_password"
```

---

## 3. Option B: Ephemeral Docker Container Setup

If you prefer not to install MySQL locally, launch an isolated container with persistent volume storage:

```bash
# Run MySQL 8.0 container
docker run -d \
  --name campus-mysql-dev \
  -e MYSQL_ROOT_PASSWORD=password123 \
  -e MYSQL_DATABASE=campus_placement_db \
  -p 3306:3306 \
  mysql:8.0

# Verify container is healthy
docker ps --filter "name=campus-mysql-dev"

# Inject schema DDL into container
docker exec -i campus-mysql-dev mysql -uroot -ppassword123 campus_placement_db < week3/schema.sql
```

---

## 4. Connectivity Diagnostics & Common Pitfalls

| Symptom | Root Cause | Remediation |
| :--- | :--- | :--- |
| `CommunicationsLinkException: Communications link failure` | MySQL service not running or port 3306 is blocked. | Verify MySQL service is listening on port 3306 via `netstat -ano \| findstr 3306`. Check firewall rules. |
| `AccessDeniedException: Access denied for user 'root'@'localhost'` | Incorrect password or missing privileges. | Reset root password or create a dedicated application user with full grants: `GRANT ALL PRIVILEGES ON campus_placement_db.* TO 'placement_app'@'%';`. |
| `SQLException: Public Key Retrieval is not allowed` | MySQL 8.0 `caching_sha2_password` requires explicit RSA key exchange when SSL is off. | Ensure JDBC URL includes `&allowPublicKeyRetrieval=true`. |
| `SQLException: The server time zone value '...' is unrecognized` | Timezone mismatch between JVM host and MySQL server. | Explicitly append `&serverTimezone=UTC` to the JDBC connection string. |
