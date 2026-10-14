-- ============================================================================
-- CAMPUS PLACEMENT AND INTERNSHIP PORTAL — RELATIONAL DATABASE SCHEMA (3NF)
-- Module 5: Database Architecture, Integration Layer & System Testing
-- Author: Himanshu Yadav (XXHimanshuXX / himanshu.yadav060107@gmail.com)
-- Target DBMS: MySQL 8.0+ (InnoDB Storage Engine)
-- Character Set: utf8mb4, Collation: utf8mb4_unicode_ci
-- ============================================================================

CREATE DATABASE IF NOT EXISTS campus_placement_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE campus_placement_db;

-- ----------------------------------------------------------------------------
-- TABLE 1: users (Central Identity and Authentication Store)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'RECRUITER', 'ADMIN') NOT NULL,
    status ENUM('ACTIVE', 'PENDING', 'INACTIVE') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_users_email (email),
    INDEX idx_users_role (role)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 2: students (Undergraduate/Postgraduate Candidate Profiles)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    roll_no VARCHAR(20) UNIQUE,
    branch VARCHAR(50),
    graduation_year INT,
    cgpa DECIMAL(4,2),
    phone VARCHAR(15),
    resume_url VARCHAR(255),
    profile_completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_students_roll_no (roll_no),
    INDEX idx_students_branch_cgpa (branch, cgpa)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 3: companies (Registered Corporate Employers and Partners)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS companies (
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    description TEXT,
    website VARCHAR(100),
    industry VARCHAR(50),
    location VARCHAR(100),
    contact_person VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_companies_name (company_name)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 4: admins (Training and Placement Office Administrative Staff)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    employee_id VARCHAR(20) UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_admins_employee_id (employee_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 5: opportunities (Placement Drives, Job & Internship Openings)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS opportunities (
    opp_id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    type ENUM('JOB', 'INTERNSHIP') NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(100),
    work_mode ENUM('ONSITE', 'REMOTE', 'HYBRID') DEFAULT 'ONSITE',
    salary_stipend VARCHAR(50),
    min_cgpa DECIMAL(4,2) DEFAULT 0.0,
    eligible_branches VARCHAR(255), -- Comma-separated eligible branches e.g. 'CSE,IT,ECE'
    graduation_year_req INT,
    required_skills VARCHAR(255),
    deadline DATE,
    status ENUM('OPEN', 'CLOSED') DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES companies(company_id) ON DELETE CASCADE,
    INDEX idx_opp_company (company_id),
    INDEX idx_opp_status_deadline (status, deadline)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 6: applications (Student Applications to Specific Opportunities)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS applications (
    application_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    opp_id INT NOT NULL,
    status ENUM('APPLIED', 'UNDER_REVIEW', 'SHORTLISTED', 'INTERVIEW_SCHEDULED', 'SELECTED', 'REJECTED') DEFAULT 'APPLIED',
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uq_student_opportunity (student_id, opp_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (opp_id) REFERENCES opportunities(opp_id) ON DELETE CASCADE,
    INDEX idx_app_student (student_id),
    INDEX idx_app_opp (opp_id),
    INDEX idx_app_status (status)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 7: application_status_history (Audit Trail for Pipeline Transitions)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS application_status_history (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    status ENUM('APPLIED', 'UNDER_REVIEW', 'SHORTLISTED', 'INTERVIEW_SCHEDULED', 'SELECTED', 'REJECTED') NOT NULL,
    remarks TEXT,
    changed_by INT,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (application_id) REFERENCES applications(application_id) ON DELETE CASCADE,
    FOREIGN KEY (changed_by) REFERENCES users(user_id) ON DELETE SET NULL,
    INDEX idx_history_app (application_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 8: interviews (Scheduled Selection Rounds and Online Evaluations)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS interviews (
    interview_id INT AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    interview_date DATE NOT NULL,
    interview_time TIME NOT NULL,
    type ENUM('TECHNICAL', 'HR', 'ONLINE_TEST') NOT NULL,
    location_link VARCHAR(255),
    instructions TEXT,
    FOREIGN KEY (application_id) REFERENCES applications(application_id) ON DELETE CASCADE,
    INDEX idx_interview_app (application_id),
    INDEX idx_interview_date (interview_date)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 9: notifications (System Notification Dispatch Log)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS notifications (
    notif_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_notif_user_unread (user_id, is_read)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 10: education (Academic History Qualifications)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS education (
    edu_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    degree VARCHAR(50),
    institution VARCHAR(100),
    passing_year INT,
    percentage DECIMAL(5,2),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    INDEX idx_edu_student (student_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 11: skills (Standardized Master Competency Index)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS skills (
    skill_id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(50) UNIQUE NOT NULL
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 12: student_skills (Normalized Candidate Skill Junction Table)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS student_skills (
    student_id INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (student_id, skill_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skills(skill_id) ON DELETE CASCADE,
    INDEX idx_student_skills_skill (skill_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 13: projects (Candidate Capstone and Independent Projects)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    link VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    INDEX idx_project_student (student_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 14: certifications (Verified Professional Accreditations)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS certifications (
    cert_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    authority VARCHAR(100),
    date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    INDEX idx_cert_student (student_id)
) ENGINE=InnoDB;

-- ----------------------------------------------------------------------------
-- TABLE 15: experiences (Internship and Industrial Work Experience)
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS experiences (
    exp_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    company VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    description TEXT,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    INDEX idx_exp_student (student_id)
) ENGINE=InnoDB;

-- ============================================================================
-- SEED INITIAL DATA FOR VERIFICATION AND LOCAL TESTING
-- ============================================================================

-- Master Skills Registry
INSERT IGNORE INTO skills (skill_name) VALUES 
('Java'), ('Python'), ('C++'), ('JavaScript'), ('React'), 
('Node.js'), ('MySQL'), ('Spring Boot'), ('HTML/CSS'), ('Data Structures'),
('Machine Learning'), ('AWS'), ('Docker'), ('Git'), ('Linux');

-- Default Administrator Account (Bcrypt hash for 'admin123')
INSERT IGNORE INTO users (user_id, email, password_hash, role, status) 
VALUES (1, 'admin@college.edu', '$2a$10$wYQe43xZ75GZ7O8G.P39GOMrA/Y5.c.eH0/mYvB/Z5y6BqKk.o9oO', 'ADMIN', 'ACTIVE');

INSERT IGNORE INTO admins (admin_id, user_id, name, employee_id) 
VALUES (1, 1, 'Placement Administrator', 'EMP001');
