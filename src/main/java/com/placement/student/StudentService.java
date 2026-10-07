package com.placement.student;

import com.placement.dao.ApplicationDAO;
import com.placement.dao.InterviewDAO;
import com.placement.dao.JobDAO;
import com.placement.dao.NotificationDAO;
import com.placement.dao.StudentDAO;
import com.placement.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Student Portal Business Logic & Operations
 * Module 2: Student Portal
 * Assigned to: Shlok (feature/student-portal)
 */
public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();
    private final JobDAO jobDAO = new JobDAO();
    private final ApplicationDAO appDAO = new ApplicationDAO();
    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final NotificationDAO notificationDAO = new NotificationDAO();

    public StudentProfile getProfileByUserId(int userId) {
        return studentDAO.findByUserId(userId);
    }

    public StudentProfile updateProfile(StudentProfile profile) {
        return studentDAO.save(profile);
    }

    /**
     * Retrieves all active jobs with calculated eligibility flag for this student
     */
    public List<Job> getEligibleJobsForStudent(int studentId) {
        StudentProfile student = studentDAO.findById(studentId);
        List<Job> allJobs = jobDAO.findActive();

        if (student == null) {
            return allJobs;
        }

        for (Job job : allJobs) {
            boolean eligible = checkEligibility(student, job);
            job.setEligible(eligible);
        }
        return allJobs;
    }

    /**
     * Core eligibility validation engine:
     * Checks CGPA threshold and eligible departments
     */
    public boolean checkEligibility(StudentProfile student, Job job) {
        if (student.getCgpa() < job.getMinCgpa()) {
            return false;
        }
        String branches = job.getEligibleBranches();
        if (branches == null || branches.equalsIgnoreCase("All Branches") || branches.trim().isEmpty()) {
            return true;
        }
        String[] allowed = branches.split("[,;]");
        for (String b : allowed) {
            if (student.getBranch().toLowerCase().contains(b.trim().toLowerCase()) ||
                b.trim().toLowerCase().contains(student.getBranch().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Submit an application for an active job opening
     */
    public Application applyForJob(int studentId, int jobId, String coverNote) {
        StudentProfile student = studentDAO.findById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student profile not found.");
        }
        Job job = jobDAO.findById(jobId);
        if (job == null || job.getStatus() != Job.Status.ACTIVE) {
            throw new IllegalArgumentException("Job opening is inactive or does not exist.");
        }
        if (!checkEligibility(student, job)) {
            throw new IllegalStateException("You do not meet the minimum eligibility criteria (CGPA/Branch) for this drive.");
        }
        Application existing = appDAO.find(studentId, jobId);
        if (existing != null) {
            throw new IllegalStateException("You have already submitted an application for this opportunity.");
        }

        Application application = new Application();
        application.setJobId(jobId);
        application.setStudentId(studentId);
        application.setStatus(Application.Status.APPLIED);
        application.setCoverNote(coverNote != null ? coverNote.trim() : "Application submitted via Campus Placement Portal.");
        Application saved = appDAO.save(application);

        // System notification to student
        Notification n = new Notification();
        n.setUserId(student.getUserId());
        n.setTitle("Application Submitted");
        n.setMessage("Your application for " + job.getTitle() + " at " + job.getCompanyName() + " was received successfully.");
        n.setRead(false);
        notificationDAO.send(n);

        return saved;
    }

    public List<Application> getStudentApplications(int studentId) {
        return appDAO.findByStudentId(studentId);
    }

    public List<Interview> getStudentInterviews(int studentId) {
        return interviewDAO.findByStudentId(studentId);
    }

    public List<Notification> getStudentNotifications(int userId) {
        return notificationDAO.findByUserId(userId);
    }

    public void markNotificationsRead(int userId) {
        notificationDAO.markAllRead(userId);
    }

    public boolean withdrawApplication(int applicationId, int studentId) {
        Application a = appDAO.findById(applicationId);
        if (a != null && a.getStudentId() == studentId) {
            return appDAO.delete(applicationId);
        }
        return false;
    }
}
