package com.placement.db;

import com.placement.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * High-Performance Thread-Safe In-Memory Relational Engine
 * Provides instant zero-configuration database functionality for campus lab environments.
 * Seeded automatically with production-grade university demo data.
 * Assigned to: Himanshu (feature/database-integration)
 */
public class InMemoryDB {
    private static final InMemoryDB INSTANCE = new InMemoryDB();

    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final Map<Integer, StudentProfile> students = new ConcurrentHashMap<>();
    private final Map<Integer, CompanyProfile> companies = new ConcurrentHashMap<>();
    private final Map<Integer, Job> jobs = new ConcurrentHashMap<>();
    private final Map<Integer, Application> applications = new ConcurrentHashMap<>();
    private final Map<Integer, Interview> interviews = new ConcurrentHashMap<>();
    private final Map<Integer, Notification> notifications = new ConcurrentHashMap<>();

    private final AtomicInteger userSeq = new AtomicInteger(10);
    private final AtomicInteger studentSeq = new AtomicInteger(10);
    private final AtomicInteger companySeq = new AtomicInteger(10);
    private final AtomicInteger jobSeq = new AtomicInteger(10);
    private final AtomicInteger appSeq = new AtomicInteger(10);
    private final AtomicInteger interviewSeq = new AtomicInteger(10);
    private final AtomicInteger notifSeq = new AtomicInteger(10);

    private InMemoryDB() {
        seedInitialData();
    }

    public static InMemoryDB getInstance() {
        return INSTANCE;
    }

    private void seedInitialData() {
        String defaultPassHash = DBUtils.hashPassword("pass123");

        // 1. Users
        User admin = new User(1, "admin@campus.edu", defaultPassHash, User.Role.ADMIN, "Dr. Krishna Sharma (TPO Head)", "+91 98765 43210", User.Status.ACTIVE);
        User googleRecruiter = new User(2, "recruiter@google.com", defaultPassHash, User.Role.RECRUITER, "Saurabh Verma (Google TA)", "+91 98111 22334", User.Status.ACTIVE);
        User msftRecruiter = new User(3, "recruiter@microsoft.com", defaultPassHash, User.Role.RECRUITER, "Elena Rostova (Microsoft Recruiter)", "+91 98222 33445", User.Status.ACTIVE);
        User amznRecruiter = new User(4, "recruiter@amazon.com", defaultPassHash, User.Role.RECRUITER, "Rajesh Iyer (AWS Recruiter)", "+91 98333 44556", User.Status.ACTIVE);

        User himanshu = new User(5, "himanshu@gmail.com", defaultPassHash, User.Role.STUDENT, "Himanshu Yadav", "+91 98444 55667", User.Status.ACTIVE);
        User shlok = new User(6, "shlok@gmail.com", defaultPassHash, User.Role.STUDENT, "Shlok Joshi", "+91 98555 66778", User.Status.ACTIVE);
        User adhiraj = new User(7, "adhiraj@gmail.com", defaultPassHash, User.Role.STUDENT, "Adhiraj Rathore", "+91 98666 77889", User.Status.ACTIVE);
        User ananya = new User(8, "ananya@gmail.com", defaultPassHash, User.Role.STUDENT, "Ananya Gupta", "+91 98777 88990", User.Status.ACTIVE);

        users.put(admin.getId(), admin);
        users.put(googleRecruiter.getId(), googleRecruiter);
        users.put(msftRecruiter.getId(), msftRecruiter);
        users.put(amznRecruiter.getId(), amznRecruiter);
        users.put(himanshu.getId(), himanshu);
        users.put(shlok.getId(), shlok);
        users.put(adhiraj.getId(), adhiraj);
        users.put(ananya.getId(), ananya);

        // 2. Student Profiles
        StudentProfile sp1 = new StudentProfile(1, 5, "22CS101", "Computer Science & Engineering", 9.40, 2026);
        sp1.setResumeUrl("https://example.com/resumes/himanshu-cv.pdf");
        sp1.setSkills("Java 24, Spring Boot, MySQL, Docker, Kubernetes, React, Distributed Systems");
        sp1.setBio("Full-stack software architect specializing in database persistence, concurrent servlets, and cloud infrastructure.");
        sp1.setLinkedinUrl("https://www.linkedin.com/in/himanshu-yadav-112ba6376");
        sp1.setGithubUrl("https://github.com/XXHimanshuXX");
        sp1.setStudentName("Himanshu Yadav");
        sp1.setStudentEmail("himanshu@gmail.com");
        sp1.setPhone("+91 98444 55667");

        StudentProfile sp2 = new StudentProfile(2, 6, "22CS102", "Computer Science & Engineering", 8.85, 2026);
        sp2.setResumeUrl("https://example.com/resumes/shlok-cv.pdf");
        sp2.setSkills("Java, Android Architecture, Flutter, REST APIs, Microservices");
        sp2.setBio("Mobile & web developer with 3 production applications deployed on app stores.");
        sp2.setLinkedinUrl("https://linkedin.com/in/shlok-joshi");
        sp2.setGithubUrl("https://github.com/shlok-joshi");
        sp2.setStudentName("Shlok Joshi");
        sp2.setStudentEmail("shlok@gmail.com");
        sp2.setPhone("+91 98555 66778");

        StudentProfile sp3 = new StudentProfile(3, 7, "22IT103", "Information Technology", 8.60, 2026);
        sp3.setResumeUrl("https://example.com/resumes/adhiraj-cv.pdf");
        sp3.setSkills("Java, OAuth2, Cryptography, Linux Security, Network Penetration Testing");
        sp3.setBio("Security engineer passionate about authentication mechanisms and zero-trust infrastructure.");
        sp3.setLinkedinUrl("https://linkedin.com/in/adhiraj-rathore");
        sp3.setGithubUrl("https://github.com/adhiraj-rathore");
        sp3.setStudentName("Adhiraj Rathore");
        sp3.setStudentEmail("adhiraj@gmail.com");
        sp3.setPhone("+91 98666 77889");

        StudentProfile sp4 = new StudentProfile(4, 8, "22EC104", "Electronics & Communication", 9.10, 2026);
        sp4.setResumeUrl("https://example.com/resumes/ananya-cv.pdf");
        sp4.setSkills("Java, Embedded C, Python, Edge AI, IoT Protocols");
        sp4.setBio("Hardware-software co-designer focusing on hardware-accelerated deep learning.");
        sp4.setLinkedinUrl("https://linkedin.com/in/ananya-gupta");
        sp4.setGithubUrl("https://github.com/ananya-gupta");
        sp4.setStudentName("Ananya Gupta");
        sp4.setStudentEmail("ananya@gmail.com");
        sp4.setPhone("+91 98777 88990");

        students.put(sp1.getId(), sp1);
        students.put(sp2.getId(), sp2);
        students.put(sp3.getId(), sp3);
        students.put(sp4.getId(), sp4);

        // 3. Company Profiles
        CompanyProfile cp1 = new CompanyProfile(1, 2, "Google India", "Information Technology & Cloud", "Bengaluru / Hyderabad");
        cp1.setWebsite("https://careers.google.com");
        cp1.setDescription("Global technology powerhouse organizing world information and building state of the art AI platforms.");
        cp1.setVerificationStatus(CompanyProfile.VerificationStatus.VERIFIED);
        cp1.setRecruiterName("Saurabh Verma (Google TA)");
        cp1.setRecruiterEmail("recruiter@google.com");
        cp1.setRecruiterPhone("+91 98111 22334");

        CompanyProfile cp2 = new CompanyProfile(2, 3, "Microsoft Corporation", "Cloud & Enterprise Software", "Bengaluru / Noida");
        cp2.setWebsite("https://careers.microsoft.com");
        cp2.setDescription("Empowering every person and organization on the planet through Azure, GitHub, and enterprise cloud.");
        cp2.setVerificationStatus(CompanyProfile.VerificationStatus.VERIFIED);
        cp2.setRecruiterName("Elena Rostova (Microsoft Recruiter)");
        cp2.setRecruiterEmail("recruiter@microsoft.com");
        cp2.setRecruiterPhone("+91 98222 33445");

        CompanyProfile cp3 = new CompanyProfile(3, 4, "Amazon Web Services", "Cloud Computing & E-Commerce", "Hyderabad / Gurugram");
        cp3.setWebsite("https://amazon.jobs");
        cp3.setDescription("World's most comprehensive cloud computing platform offering over 200 fully featured services globally.");
        cp3.setVerificationStatus(CompanyProfile.VerificationStatus.VERIFIED);
        cp3.setRecruiterName("Rajesh Iyer (AWS Recruiter)");
        cp3.setRecruiterEmail("recruiter@amazon.com");
        cp3.setRecruiterPhone("+91 98333 44556");

        companies.put(cp1.getId(), cp1);
        companies.put(cp2.getId(), cp2);
        companies.put(cp3.getId(), cp3);

        // 4. Jobs & Internships
        long now = System.currentTimeMillis();
        long monthLater = now + (30L * 24 * 3600 * 1000);

        Job j1 = new Job(1, 1, "Software Development Engineer - SWE 2026", Job.JobType.FULL_TIME, 28.50, 0,
                "Bengaluru", 8.50, "Computer Science & Engineering, Information Technology", new Date(monthLater), Job.Status.ACTIVE);
        j1.setDescription("Architect distributed backend systems handling billions of search and cloud requests with ultra-low latency.");
        j1.setCompanyName("Google India");
        j1.setIndustry("Information Technology & Cloud");
        j1.setApplicantCount(2);

        Job j2 = new Job(2, 1, "Cloud Engineering Summer Intern", Job.JobType.INTERNSHIP, 0, 110000.0,
                "Bengaluru", 8.00, "All Branches", new Date(monthLater), Job.Status.ACTIVE);
        j2.setDescription("Build autonomous infrastructure automation tools on Google Cloud Platform (GCP). 6 months summer internship.");
        j2.setCompanyName("Google India");
        j2.setIndustry("Information Technology & Cloud");
        j2.setApplicantCount(1);

        Job j3 = new Job(3, 2, "Software Engineer - Azure Core", Job.JobType.FULL_TIME, 25.00, 0,
                "Hyderabad", 8.00, "Computer Science & Engineering, Information Technology, Electronics & Communication", new Date(monthLater), Job.Status.ACTIVE);
        j3.setDescription("Develop core cloud virtualization primitives, microsecond kernel drivers, and hyper-scalable storage.");
        j3.setCompanyName("Microsoft Corporation");
        j3.setIndustry("Cloud & Enterprise Software");
        j3.setApplicantCount(1);

        Job j4 = new Job(4, 3, "Systems Development Engineer - AWS", Job.JobType.FULL_TIME, 24.00, 0,
                "Hyderabad", 7.50, "All Branches", new Date(monthLater), Job.Status.ACTIVE);
        j4.setDescription("Design resilient, zero-downtime microservices and telemetry monitoring frameworks across AWS global regions.");
        j4.setCompanyName("Amazon Web Services");
        j4.setIndustry("Cloud Computing & E-Commerce");
        j4.setApplicantCount(1);

        jobs.put(j1.getId(), j1);
        jobs.put(j2.getId(), j2);
        jobs.put(j3.getId(), j3);
        jobs.put(j4.getId(), j4);

        // 5. Applications
        Application app1 = new Application(1, 1, 1, Application.Status.SELECTED, "Strong interest in high-scale Java distributed backend architecture.");
        app1.setJobTitle("Software Development Engineer - SWE 2026");
        app1.setCompanyName("Google India");
        app1.setJobType("FULL_TIME");
        app1.setPackageLpa(28.50);
        app1.setStudentName("Himanshu Yadav");
        app1.setStudentEmail("himanshu@gmail.com");
        app1.setRollNumber("22CS101");
        app1.setBranch("Computer Science & Engineering");
        app1.setCgpa(9.40);
        app1.setResumeUrl("https://example.com/resumes/himanshu-cv.pdf");
        app1.setAppliedAt(new Timestamp(now - (5L * 24 * 3600 * 1000)));

        Application app2 = new Application(2, 2, 1, Application.Status.SELECTED, "Cloud passionate with extensive GCP container deployment experience.");
        app2.setJobTitle("Cloud Engineering Summer Intern");
        app2.setCompanyName("Google India");
        app2.setJobType("INTERNSHIP");
        app2.setStipendPm(110000.0);
        app2.setStudentName("Himanshu Yadav");
        app2.setStudentEmail("himanshu@gmail.com");
        app2.setRollNumber("22CS101");
        app2.setBranch("Computer Science & Engineering");
        app2.setCgpa(9.40);
        app2.setResumeUrl("https://example.com/resumes/himanshu-cv.pdf");
        app2.setAppliedAt(new Timestamp(now - (8L * 24 * 3600 * 1000)));

        Application app3 = new Application(3, 1, 2, Application.Status.INTERVIEW_SCHEDULED, "Extensive mobile and microservices full-stack portfolio.");
        app3.setJobTitle("Software Development Engineer - SWE 2026");
        app3.setCompanyName("Google India");
        app3.setJobType("FULL_TIME");
        app3.setPackageLpa(28.50);
        app3.setStudentName("Shlok Joshi");
        app3.setStudentEmail("shlok@gmail.com");
        app3.setRollNumber("22CS102");
        app3.setBranch("Computer Science & Engineering");
        app3.setCgpa(8.85);
        app3.setResumeUrl("https://example.com/resumes/shlok-cv.pdf");
        app3.setAppliedAt(new Timestamp(now - (3L * 24 * 3600 * 1000)));

        Application app4 = new Application(4, 3, 3, Application.Status.APPLIED, "Passionate about security compliance and identity systems.");
        app4.setJobTitle("Software Engineer - Azure Core");
        app4.setCompanyName("Microsoft Corporation");
        app4.setJobType("FULL_TIME");
        app4.setPackageLpa(25.00);
        app4.setStudentName("Adhiraj Rathore");
        app4.setStudentEmail("adhiraj@gmail.com");
        app4.setRollNumber("22IT103");
        app4.setBranch("Information Technology");
        app4.setCgpa(8.60);
        app4.setResumeUrl("https://example.com/resumes/adhiraj-cv.pdf");
        app4.setAppliedAt(new Timestamp(now - (1L * 24 * 3600 * 1000)));

        Application app5 = new Application(5, 4, 4, Application.Status.SHORTLISTED, "Strong algorithmic background and IoT systems optimization.");
        app5.setJobTitle("Systems Development Engineer - AWS");
        app5.setCompanyName("Amazon Web Services");
        app5.setJobType("FULL_TIME");
        app5.setPackageLpa(24.00);
        app5.setStudentName("Ananya Gupta");
        app5.setStudentEmail("ananya@gmail.com");
        app5.setRollNumber("22EC104");
        app5.setBranch("Electronics & Communication");
        app5.setCgpa(9.10);
        app5.setResumeUrl("https://example.com/resumes/ananya-cv.pdf");
        app5.setAppliedAt(new Timestamp(now - (2L * 24 * 3600 * 1000)));

        applications.put(app1.getId(), app1);
        applications.put(app2.getId(), app2);
        applications.put(app3.getId(), app3);
        applications.put(app4.getId(), app4);
        applications.put(app5.getId(), app5);

        // 6. Interviews
        Interview i1 = new Interview(1, 1, "Technical Architecture Round 1",
                new Timestamp(now + (2L * 24 * 3600 * 1000)), "https://meet.google.com/xyz-tech1", Interview.Mode.ONLINE, Interview.Status.SCHEDULED);
        i1.setStudentName("Himanshu Yadav");
        i1.setStudentEmail("himanshu@gmail.com");
        i1.setJobTitle("Software Development Engineer - SWE 2026");
        i1.setCompanyName("Google India");
        i1.setFeedback("Deep evaluation of concurrency models, memory leak prevention, and query tuning.");

        Interview i2 = new Interview(2, 3, "Algorithms & Problem Solving",
                new Timestamp(now + (3L * 24 * 3600 * 1000)), "https://meet.google.com/abc-code", Interview.Mode.ONLINE, Interview.Status.SCHEDULED);
        i2.setStudentName("Shlok Joshi");
        i2.setStudentEmail("shlok@gmail.com");
        i2.setJobTitle("Software Development Engineer - SWE 2026");
        i2.setCompanyName("Google India");
        i2.setFeedback("Problem solving round with live code collaboration.");

        interviews.put(i1.getId(), i1);
        interviews.put(i2.getId(), i2);

        // 7. Notifications
        Notification n1 = new Notification(1, 5, "Application Shortlisted!", "Congratulations Himanshu! You have been shortlisted for Google SWE 2026 drive.", false);
        Notification n2 = new Notification(2, 5, "Official Offer Extended!", "Google India has officially released an offer for Cloud Engineering Summer 2026!", false);
        Notification n3 = new Notification(3, 6, "Interview Scheduled", "Your Technical Round with Google India is confirmed for upcoming Friday at 14:00 IST.", false);
        Notification n4 = new Notification(4, 1, "Placement Drive Update", "4 Top tier tech companies have posted drives with 100% verification complete.", true);

        notifications.put(n1.getId(), n1);
        notifications.put(n2.getId(), n2);
        notifications.put(n3.getId(), n3);
        notifications.put(n4.getId(), n4);
    }

    // --- Users API ---
    public User findUserByEmail(String email) {
        if (email == null) return null;
        for (User u : users.values()) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) return u;
        }
        return null;
    }

    public User findUserById(int id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User createUser(User user) {
        int id = userSeq.incrementAndGet();
        user.setId(id);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        users.put(id, user);
        return user;
    }

    public void updateUser(User user) {
        users.put(user.getId(), user);
    }

    // --- Students API ---
    public StudentProfile findStudentByUserId(int userId) {
        for (StudentProfile sp : students.values()) {
            if (sp.getUserId() == userId) return sp;
        }
        return null;
    }

    public StudentProfile findStudentById(int id) {
        return students.get(id);
    }

    public List<StudentProfile> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public StudentProfile saveStudent(StudentProfile sp) {
        if (sp.getId() <= 0) {
            int id = studentSeq.incrementAndGet();
            sp.setId(id);
        }
        User u = users.get(sp.getUserId());
        if (u != null && sp.getStudentName() == null) {
            sp.setStudentName(u.getFullName());
        }
        students.put(sp.getId(), sp);
        for (Application a : applications.values()) {
            if (a.getStudentId() == sp.getId()) {
                if (sp.getStudentName() != null) a.setStudentName(sp.getStudentName());
                if (sp.getRollNumber() != null) a.setRollNumber(sp.getRollNumber());
                if (sp.getBranch() != null) a.setBranch(sp.getBranch());
                a.setCgpa(sp.getCgpa());
                if (sp.getResumeUrl() != null) a.setResumeUrl(sp.getResumeUrl());
            }
        }
        return sp;
    }

    // --- Companies API ---
    public CompanyProfile findCompanyByUserId(int userId) {
        for (CompanyProfile cp : companies.values()) {
            if (cp.getUserId() == userId) return cp;
        }
        return null;
    }

    public CompanyProfile findCompanyById(int id) {
        return companies.get(id);
    }

    public List<CompanyProfile> getAllCompanies() {
        return new ArrayList<>(companies.values());
    }

    public CompanyProfile saveCompany(CompanyProfile cp) {
        if (cp.getId() <= 0) {
            int id = companySeq.incrementAndGet();
            cp.setId(id);
        }
        companies.put(cp.getId(), cp);
        return cp;
    }

    // --- Jobs API ---
    public List<Job> getAllJobs() {
        List<Job> list = new ArrayList<>(jobs.values());
        list.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        return list;
    }

    public List<Job> getJobsByCompany(int companyId) {
        List<Job> list = new ArrayList<>();
        for (Job j : jobs.values()) {
            if (j.getCompanyId() == companyId) list.add(j);
        }
        list.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        return list;
    }

    public Job findJobById(int id) {
        return jobs.get(id);
    }

    public Job saveJob(Job job) {
        if (job.getId() <= 0) {
            int id = jobSeq.incrementAndGet();
            job.setId(id);
            job.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        jobs.put(job.getId(), job);
        return job;
    }

    public boolean deleteJob(int id) {
        return jobs.remove(id) != null;
    }

    // --- Applications API ---
    public List<Application> getApplicationsByStudent(int studentId) {
        List<Application> list = new ArrayList<>();
        for (Application a : applications.values()) {
            if (a.getStudentId() == studentId) list.add(a);
        }
        list.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        return list;
    }

    public List<Application> getApplicationsByJob(int jobId) {
        List<Application> list = new ArrayList<>();
        for (Application a : applications.values()) {
            if (a.getJobId() == jobId) list.add(a);
        }
        return list;
    }

    public List<Application> getAllApplications() {
        return new ArrayList<>(applications.values());
    }

    public Application findApplicationById(int id) {
        return applications.get(id);
    }

    public Application findApplication(int studentId, int jobId) {
        for (Application a : applications.values()) {
            if (a.getStudentId() == studentId && a.getJobId() == jobId) return a;
        }
        return null;
    }

    public Application saveApplication(Application app) {
        if (app.getId() <= 0) {
            int id = appSeq.incrementAndGet();
            app.setId(id);
            app.setAppliedAt(new Timestamp(System.currentTimeMillis()));
        }
        app.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        applications.put(app.getId(), app);

        // Update count on Job
        Job j = jobs.get(app.getJobId());
        if (j != null) {
            int count = 0;
            for (Application a : applications.values()) {
                if (a.getJobId() == j.getId()) count++;
            }
            j.setApplicantCount(count);
        }
        return app;
    }

    public boolean deleteApplication(int id) {
        Application removed = applications.remove(id);
        if (removed != null) {
            Job j = jobs.get(removed.getJobId());
            if (j != null) {
                int count = 0;
                for (Application a : applications.values()) {
                    if (a.getJobId() == j.getId()) count++;
                }
                j.setApplicantCount(count);
            }
            return true;
        }
        return false;
    }

    // --- Interviews API ---
    public List<Interview> getAllInterviews() {
        return new ArrayList<>(interviews.values());
    }

    public List<Interview> getInterviewsByStudent(int studentId) {
        List<Interview> list = new ArrayList<>();
        for (Interview i : interviews.values()) {
            Application a = applications.get(i.getApplicationId());
            if (a != null && a.getStudentId() == studentId) {
                list.add(i);
            }
        }
        return list;
    }

    public List<Interview> getInterviewsByCompany(int companyId) {
        List<Interview> list = new ArrayList<>();
        for (Interview i : interviews.values()) {
            Application a = applications.get(i.getApplicationId());
            if (a != null) {
                Job j = jobs.get(a.getJobId());
                if (j != null && j.getCompanyId() == companyId) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    public Interview saveInterview(Interview interview) {
        if (interview.getId() <= 0) {
            int id = interviewSeq.incrementAndGet();
            interview.setId(id);
            interview.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        interviews.put(interview.getId(), interview);
        return interview;
    }

    // --- Notifications API ---
    public List<Notification> getNotificationsByUser(int userId) {
        List<Notification> list = new ArrayList<>();
        for (Notification n : notifications.values()) {
            if (n.getUserId() == userId) list.add(n);
        }
        list.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        return list;
    }

    public Notification saveNotification(Notification n) {
        if (n.getId() <= 0) {
            int id = notifSeq.incrementAndGet();
            n.setId(id);
            n.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        notifications.put(n.getId(), n);
        return n;
    }

    public void markAllNotificationsRead(int userId) {
        for (Notification n : notifications.values()) {
            if (n.getUserId() == userId) n.setRead(true);
        }
    }
}
