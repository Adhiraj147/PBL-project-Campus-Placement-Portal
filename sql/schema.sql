-- =========================================================================
-- CAMPUS PLACEMENT AND INTERNSHIP PORTAL
-- Database Schema (MySQL 8.0+ / MariaDB / ANSI SQL Compliant)
-- Module 5: Database, Integration & Testing (Himanshu)
-- =========================================================================

CREATE DATABASE IF NOT EXISTS campus_placement_db;
USE campus_placement_db;

-- -------------------------------------------------------------------------
-- 1. USERS TABLE (Authentication & Role-Based Access Control - Adhiraj)
-- -------------------------------------------------------------------------
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS interviews;
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS company_profiles;
DROP TABLE IF EXISTS student_profiles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('STUDENT', 'RECRUITER', 'ADMIN') NOT NULL,
    full_name VARCHAR(120) NOT NULL,
    phone VARCHAR(20),
    status ENUM('ACTIVE', 'PENDING', 'SUSPENDED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_user_role (role),
    INDEX idx_user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 2. STUDENT PROFILES TABLE (Student Portal - Shlok)
-- -------------------------------------------------------------------------
CREATE TABLE student_profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    roll_number VARCHAR(50) NOT NULL UNIQUE,
    branch VARCHAR(100) NOT NULL,
    cgpa DECIMAL(3,2) NOT NULL,
    graduation_year INT NOT NULL,
    resume_url VARCHAR(255),
    skills TEXT,
    bio TEXT,
    linkedin_url VARCHAR(255),
    github_url VARCHAR(255),
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_student_cgpa (cgpa),
    INDEX idx_student_branch (branch)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 3. COMPANY PROFILES TABLE (Recruiter Portal - Saurabh)
-- -------------------------------------------------------------------------
CREATE TABLE company_profiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    company_name VARCHAR(150) NOT NULL,
    industry VARCHAR(100) NOT NULL,
    website VARCHAR(255),
    location VARCHAR(100) NOT NULL,
    description TEXT,
    verification_status ENUM('PENDING', 'VERIFIED', 'REJECTED') DEFAULT 'PENDING',
    verified_at TIMESTAMP NULL,
    CONSTRAINT fk_company_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_company_status (verification_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 4. JOBS TABLE (Recruiter & Admin Moderation - Saurabh & Krishna)
-- -------------------------------------------------------------------------
CREATE TABLE jobs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    job_type ENUM('FULL_TIME', 'INTERNSHIP') NOT NULL,
    package_lpa DECIMAL(5,2) DEFAULT 0.00,
    stipend_pm DECIMAL(10,2) DEFAULT 0.00,
    location VARCHAR(100) NOT NULL,
    min_cgpa DECIMAL(3,2) NOT NULL DEFAULT 6.00,
    eligible_branches VARCHAR(255) NOT NULL DEFAULT 'All Branches',
    deadline DATE NOT NULL,
    status ENUM('PENDING_APPROVAL', 'ACTIVE', 'CLOSED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_job_company FOREIGN KEY (company_id) 
        REFERENCES company_profiles(id) ON DELETE CASCADE,
    INDEX idx_job_status (status),
    INDEX idx_job_type (job_type),
    INDEX idx_job_min_cgpa (min_cgpa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 5. APPLICATIONS TABLE (Student & Recruiter Interaction - Shlok & Saurabh)
-- -------------------------------------------------------------------------
CREATE TABLE applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    job_id INT NOT NULL,
    student_id INT NOT NULL,
    status ENUM('APPLIED', 'SHORTLISTED', 'INTERVIEW_SCHEDULED', 'SELECTED', 'REJECTED') DEFAULT 'APPLIED',
    cover_note TEXT,
    applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_app_job FOREIGN KEY (job_id) 
        REFERENCES jobs(id) ON DELETE CASCADE,
    CONSTRAINT fk_app_student FOREIGN KEY (student_id) 
        REFERENCES student_profiles(id) ON DELETE CASCADE,
    UNIQUE KEY uk_student_job (student_id, job_id),
    INDEX idx_app_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 6. INTERVIEWS TABLE (Recruiter Workflow & Notifications - Saurabh & Himanshu)
-- -------------------------------------------------------------------------
CREATE TABLE interviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    application_id INT NOT NULL,
    round_name VARCHAR(100) NOT NULL,
    scheduled_time DATETIME NOT NULL,
    meeting_link VARCHAR(255) NOT NULL,
    mode ENUM('ONLINE', 'IN_PERSON') DEFAULT 'ONLINE',
    status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED') DEFAULT 'SCHEDULED',
    feedback TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_interview_app FOREIGN KEY (application_id) 
        REFERENCES applications(id) ON DELETE CASCADE,
    INDEX idx_interview_time (scheduled_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- -------------------------------------------------------------------------
-- 7. NOTIFICATIONS TABLE (System Integration - Himanshu)
-- -------------------------------------------------------------------------
CREATE TABLE notifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_notif_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_notif_user (user_id, is_read)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
