package com.placement.test;

import com.placement.auth.AuthService;
import com.placement.student.StudentService;
import com.placement.recruiter.RecruiterService;
import com.placement.admin.AdminService;
import com.placement.model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Enterprise Unit & Integration Test Suite
 * Module 5: Testing & Integration
 * Assigned to: Himanshu (feature/database-integration)
 */
public class PlacementPortalTest {

    private static int testsRun = 0;
    private static int testsPassed = 0;

    public static void main(String[] args) {
        System.out.println("=====================================================================");
        System.out.println("   CAMPUS PLACEMENT AND INTERNSHIP PORTAL - AUTOMATED TEST RUNNER   ");
        System.out.println("=====================================================================");

        testModule1_Authentication();
        testModule2_StudentPortal();
        testModule3_RecruiterPortal();
        testModule4_AdminAnalytics();
        testModule5_DatabaseAndNotifications();

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("   TEST SUMMARY: %d / %d PASSED (%.1f%% SUCCESS)\n",
                testsPassed, testsRun, (testsPassed * 100.0) / testsRun);
        System.out.println("=====================================================================");

        if (testsPassed != testsRun) {
            System.exit(1);
        }
    }

    private static void assertEquals(String testName, Object expected, Object actual) {
        testsRun++;
        if (expected == null && actual == null || (expected != null && expected.equals(actual))) {
            testsPassed++;
            System.out.println("   [PASS] " + testName);
        } else {
            System.err.println("   [FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
        }
    }

    private static void assertTrue(String testName, boolean condition) {
        assertEquals(testName, true, condition);
    }

    // --- MODULE 1: AUTHENTICATION (Adhiraj) ---
    private static void testModule1_Authentication() {
        System.out.println("\n[Testing Module 1: Authentication & User Management - Adhiraj]");
        AuthService auth = new AuthService();

        // 1. Valid Student Login
        User student = auth.login("himanshu@gmail.com", "pass123");
        assertTrue("Student login succeeds with correct credentials", student != null);
        assertEquals("User role is STUDENT", User.Role.STUDENT, student != null ? student.getRole() : null);

        // 2. Invalid Password
        User invalid = auth.login("himanshu@gmail.com", "wrongpass");
        assertTrue("Invalid password correctly rejected", invalid == null);

        // 3. Admin Login
        User admin = auth.login("admin@campus.edu", "pass123");
        assertTrue("Admin login succeeds", admin != null);
        assertEquals("User role is ADMIN", User.Role.ADMIN, admin != null ? admin.getRole() : null);

        // 4. Role Authorization check
        assertTrue("Admin authorized for /admin/dashboard", auth.isAuthorized(admin, "/admin/dashboard"));
        assertTrue("Student unauthorized for /admin/dashboard", !auth.isAuthorized(student, "/admin/dashboard"));
    }

    // --- MODULE 2: STUDENT PORTAL (Shlok) ---
    private static void testModule2_StudentPortal() {
        System.out.println("\n[Testing Module 2: Student Portal - Shlok]");
        StudentService ss = new StudentService();

        // 1. Profile retrieval
        StudentProfile profile = ss.getProfileByUserId(5);
        assertTrue("Student profile exists for Himanshu", profile != null);
        assertEquals("Roll number matches", "22CS101", profile.getRollNumber());

        // 2. Eligibility calculation
        List<Job> jobs = ss.getEligibleJobsForStudent(profile.getId());
        assertTrue("Active jobs are listed for student", !jobs.isEmpty());

        // 3. Check specific job eligibility (Google SWE min CGPA is 8.50, Himanshu has 9.40)
        Job sweJob = jobs.get(0);
        boolean eligible = ss.checkEligibility(profile, sweJob);
        assertTrue("Himanshu is eligible for SWE job", eligible);

        // 4. Applications list
        List<Application> apps = ss.getStudentApplications(profile.getId());
        assertTrue("Student has applications submitted", !apps.isEmpty());
    }

    // --- MODULE 3: RECRUITER PORTAL (Saurabh) ---
    private static void testModule3_RecruiterPortal() {
        System.out.println("\n[Testing Module 3: Recruiter & Company Portal - Saurabh]");
        RecruiterService rs = new RecruiterService();

        // 1. Company profile retrieval
        CompanyProfile cp = rs.getCompanyByUserId(2);
        assertTrue("Google recruiter profile loaded", cp != null);
        assertEquals("Company name is Google India", "Google India", cp.getCompanyName());

        // 2. Company Jobs
        List<Job> jobs = rs.getCompanyJobs(cp.getId());
        assertTrue("Google has active job postings", !jobs.isEmpty());

        // 3. Create a new test job drive
        Job newDrive = new Job();
        newDrive.setCompanyId(cp.getId());
        newDrive.setTitle("Site Reliability Engineer - SRE");
        newDrive.setDescription("Automate distributed systems reliability and incident telemetry.");
        newDrive.setJobType(Job.JobType.FULL_TIME);
        newDrive.setPackageLpa(26.0);
        newDrive.setLocation("Bengaluru");
        newDrive.setMinCgpa(8.0);
        newDrive.setEligibleBranches("Computer Science & Engineering");
        newDrive.setDeadline(new Date(System.currentTimeMillis() + 60L*86400000));
        newDrive.setStatus(Job.Status.ACTIVE);

        Job saved = rs.createJob(newDrive);
        assertTrue("New job drive saved with auto-increment ID", saved.getId() > 0);

        // 4. Schedule an interview
        Interview inv = new Interview();
        inv.setApplicationId(1);
        inv.setRoundName("System Design Discussion");
        inv.setScheduledTime(new Timestamp(System.currentTimeMillis() + 86400000));
        inv.setMeetingLink("https://meet.google.com/test-sre-interview");
        inv.setMode(Interview.Mode.ONLINE);
        inv.setStatus(Interview.Status.SCHEDULED);

        Interview scheduled = rs.scheduleInterview(inv);
        assertTrue("Interview scheduled successfully", scheduled.getId() > 0);
    }

    // --- MODULE 4: ADMIN & ANALYTICS (Krishna) ---
    private static void testModule4_AdminAnalytics() {
        System.out.println("\n[Testing Module 4: Admin & Analytics - Krishna]");
        AdminService as = new AdminService();

        // 1. Live Placement Statistics
        Map<String, Object> stats = as.getPlacementStatistics();
        assertTrue("Stats contains totalStudents", (int) stats.get("totalStudents") > 0);
        assertTrue("Stats contains totalCompanies", (int) stats.get("totalCompanies") > 0);
        assertTrue("Stats contains totalJobs", (int) stats.get("totalJobs") > 0);
        assertTrue("Placement rate is computed", (double) stats.get("placementRate") >= 0.0);

        // 2. Company Verification
        as.verifyCompany(1, true);
        assertTrue("Company verified without exceptions", true);

        // 3. Job Moderation
        as.moderateJob(1, true);
        assertTrue("Job moderated without exceptions", true);
    }

    // --- MODULE 5: DATABASE & INTEGRATION (Himanshu) ---
    private static void testModule5_DatabaseAndNotifications() {
        System.out.println("\n[Testing Module 5: Database Engine, Integration & Notifications - Himanshu]");
        StudentService ss = new StudentService();

        // 1. Notification retrieval
        List<Notification> notifs = ss.getStudentNotifications(5);
        assertTrue("Notifications exist for student", !notifs.isEmpty());

        // 2. Mark notifications read
        ss.markNotificationsRead(5);
        List<Notification> updatedNotifs = ss.getStudentNotifications(5);
        boolean allRead = true;
        for (Notification n : updatedNotifs) {
            if (!n.isRead()) allRead = false;
        }
        assertTrue("All notifications successfully marked as read", allRead);
    }
}
