-- =========================================================================
-- CAMPUS PLACEMENT AND INTERNSHIP PORTAL
-- Seed Data (Production Mock & Testing)
-- Module 5: Database, Integration & Testing (Himanshu)
-- =========================================================================

USE campus_placement_db;

-- -------------------------------------------------------------------------
-- 1. SEED USERS (Passwords hashed with SHA-256 for test demo: 'pass123')
-- SHA-256('pass123') = b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2
-- -------------------------------------------------------------------------
INSERT INTO users (id, email, password_hash, role, full_name, phone, status) VALUES
(1, 'admin@campus.edu', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'ADMIN', 'Dr. Krishna Sharma (TPO Head)', '+91 98765 43210', 'ACTIVE'),
(2, 'recruiter@google.com', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'RECRUITER', 'Saurabh Verma (Google Talent Acquisition)', '+91 98111 22334', 'ACTIVE'),
(3, 'recruiter@microsoft.com', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'RECRUITER', 'Elena Rostova (Microsoft University Relations)', '+91 98222 33445', 'ACTIVE'),
(4, 'recruiter@amazon.com', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'RECRUITER', 'Rajesh Iyer (Amazon Campus Recruiter)', '+91 98333 44556', 'ACTIVE'),
(5, 'himanshu@campus.edu', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'STUDENT', 'Himanshu Yadav', '+91 98444 55667', 'ACTIVE'),
(6, 'shlok@campus.edu', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'STUDENT', 'Shlok Joshi', '+91 98555 66778', 'ACTIVE'),
(7, 'adhiraj@campus.edu', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'STUDENT', 'Adhiraj Rathore', '+91 98666 77889', 'ACTIVE'),
(8, 'ananya@campus.edu', 'b9b3297a7e8412674e76c729eec011030e460be0ad23d70f90e5f0d9841c60b2', 'STUDENT', 'Ananya Gupta', '+91 98777 88990', 'ACTIVE');

-- -------------------------------------------------------------------------
-- 2. SEED STUDENT PROFILES
-- -------------------------------------------------------------------------
INSERT INTO student_profiles (id, user_id, roll_number, branch, cgpa, graduation_year, resume_url, skills, bio, linkedin_url, github_url) VALUES
(1, 5, '22CS101', 'Computer Science & Engineering', 9.40, 2026, 'https://drive.google.com/himanshu-resume.pdf', 'Java, Spring Boot, MySQL, Docker, Kubernetes, React', 'Full-stack software engineer passionate about high-concurrency systems and database performance optimization.', 'https://linkedin.com/in/himanshu-yadav', 'https://github.com/himanshu-yadav'),
(2, 6, '22CS102', 'Computer Science & Engineering', 8.85, 2026, 'https://drive.google.com/shlok-resume.pdf', 'Java, Android, Flutter, Microservices, Node.js', 'Mobile and distributed systems enthusiast with 3 production apps shipped.', 'https://linkedin.com/in/shlok-joshi', 'https://github.com/shlok-joshi'),
(3, 7, '22IT103', 'Information Technology', 8.60, 2026, 'https://drive.google.com/adhiraj-resume.pdf', 'Java, Python, Cybersecurity, OAuth2, Linux Administration', 'Security analyst focusing on RBAC systems and identity governance.', 'https://linkedin.com/in/adhiraj-rathore', 'https://github.com/adhiraj-rathore'),
(4, 8, '22EC104', 'Electronics & Communication', 9.10, 2026, 'https://drive.google.com/ananya-resume.pdf', 'Embedded C, Python, Machine Learning, IoT, Java', 'Hardware-software co-designer and AI on edge researcher.', 'https://linkedin.com/in/ananya-gupta', 'https://github.com/ananya-gupta');

-- -------------------------------------------------------------------------
-- 3. SEED COMPANY PROFILES
-- -------------------------------------------------------------------------
INSERT INTO company_profiles (id, user_id, company_name, industry, website, location, description, verification_status, verified_at) VALUES
(1, 2, 'Google India', 'Information Technology & Cloud', 'https://careers.google.com', 'Bengaluru / Hyderabad', 'Global technology leader organizing world information and making it universally accessible.', 'VERIFIED', NOW()),
(2, 3, 'Microsoft Corporation', 'Cloud & Enterprise Software', 'https://careers.microsoft.com', 'Bengaluru / Noida', 'Empowering every person and organization on the planet to achieve more.', 'VERIFIED', NOW()),
(3, 4, 'Amazon Web Services', 'E-commerce & Cloud Computing', 'https://amazon.jobs', 'Hyderabad / Gurugram', 'Earth\'s most customer-centric company and world leader in cloud infrastructure.', 'VERIFIED', NOW());

-- -------------------------------------------------------------------------
-- 4. SEED JOBS & INTERNSHIPS
-- -------------------------------------------------------------------------
INSERT INTO jobs (id, company_id, title, description, job_type, package_lpa, stipend_pm, location, min_cgpa, eligible_branches, deadline, status) VALUES
(1, 1, 'Software Development Engineer - SWE', 'Architect scalable cloud services, distributed caches, and backend APIs using Java/Go.', 'FULL_TIME', 28.50, 0, 'Bengaluru', 8.50, 'Computer Science & Engineering, Information Technology', '2026-11-15', 'ACTIVE'),
(2, 1, 'Cloud Engineering Summer Intern', 'Build automated cloud migration workflows on Google Cloud Platform (GCP).', 'INTERNSHIP', 0, 110000.00, 'Bengaluru', 8.00, 'All Branches', '2026-11-01', 'ACTIVE'),
(3, 2, 'Software Engineer - Azure Core', 'Design resilient low-latency networking and virtualization layers for Azure Cloud.', 'FULL_TIME', 25.00, 0, 'Hyderabad', 8.00, 'Computer Science & Engineering, Information Technology, Electronics & Communication', '2026-11-20', 'ACTIVE'),
(4, 3, 'Systems Development Engineer - AWS', 'Develop distributed telemetry collection engines handling millions of TPS.', 'FULL_TIME', 24.00, 0, 'Hyderabad', 7.50, 'All Branches', '2026-12-05', 'ACTIVE');

-- -------------------------------------------------------------------------
-- 5. SEED APPLICATIONS
-- -------------------------------------------------------------------------
INSERT INTO applications (id, job_id, student_id, status, cover_note, applied_at) VALUES
(1, 1, 1, 'SHORTLISTED', 'Strong background in Java enterprise concurrency and database engines.', NOW()),
(2, 2, 1, 'SELECTED', 'Passionate about cloud systems and infrastructure-as-code.', NOW()),
(3, 1, 2, 'INTERVIEW_SCHEDULED', 'Skilled in high-performance backends and mobile platforms.', NOW()),
(4, 3, 3, 'APPLIED', 'Interested in cloud security and zero-trust architectures.', NOW()),
(5, 4, 4, 'SHORTLISTED', 'Excited about distributed telemetry and real-time processing.', NOW());

-- -------------------------------------------------------------------------
-- 6. SEED INTERVIEWS
-- -------------------------------------------------------------------------
INSERT INTO interviews (id, application_id, round_name, scheduled_time, meeting_link, mode, status, feedback) VALUES
(1, 1, 'Technical Architecture Round 1', DATE_ADD(NOW(), INTERVAL 2 DAY), 'https://meet.google.com/xyz-placement-tech1', 'ONLINE', 'SCHEDULED', 'Review distributed data structures and SQL query optimization.'),
(2, 3, 'Coding & Problem Solving', DATE_ADD(NOW(), INTERVAL 3 DAY), 'https://meet.google.com/abc-placement-code', 'ONLINE', 'SCHEDULED', 'LeetCode style algorithmic interview on Trees & DP.');

-- -------------------------------------------------------------------------
-- 7. SEED NOTIFICATIONS
-- -------------------------------------------------------------------------
INSERT INTO notifications (id, user_id, title, message, is_read) VALUES
(1, 5, 'Application Shortlisted!', 'Congratulations Himanshu! You have been shortlisted for Google SWE 2026 drive.', FALSE),
(2, 5, 'Offer Letter Released!', 'Microsoft has released an internship offer for Cloud Engineering Summer 2026!', FALSE),
(3, 6, 'Interview Scheduled', 'Your Technical Round with Google India is scheduled on 2026-10-10 at 14:00 IST.', FALSE),
(4, 1, 'New Company Registered', 'Amazon Web Services has completed registration and submitted 1 new job drive.', TRUE);
