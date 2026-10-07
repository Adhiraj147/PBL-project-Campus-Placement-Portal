package com.placement.recruiter;

import com.placement.dao.*;
import com.placement.model.*;

import java.util.List;

/**
 * Recruiter & Corporate Management Service
 * Module 3: Recruiter & Company Portal
 * Assigned to: Saurabh (feature/recruiter-portal)
 */
public class RecruiterService {
    private final CompanyDAO companyDAO = new CompanyDAO();
    private final JobDAO jobDAO = new JobDAO();
    private final ApplicationDAO appDAO = new ApplicationDAO();
    private final InterviewDAO interviewDAO = new InterviewDAO();
    private final NotificationDAO notificationDAO = new NotificationDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    public CompanyProfile getCompanyByUserId(int userId) {
        return companyDAO.findByUserId(userId);
    }

    public CompanyProfile updateCompanyProfile(CompanyProfile profile) {
        return companyDAO.save(profile);
    }

    public List<Job> getCompanyJobs(int companyId) {
        return jobDAO.findByCompanyId(companyId);
    }

    public Job createJob(Job job) {
        return jobDAO.save(job);
    }

    public Job updateJob(Job job) {
        return jobDAO.save(job);
    }

    public boolean deleteJob(int jobId) {
        return jobDAO.delete(jobId);
    }

    public List<Application> getJobApplicants(int jobId) {
        return appDAO.findByJobId(jobId);
    }

    /**
     * Shortlist or Reject applicant with instant notification
     */
    public void updateApplicantStatus(int applicationId, Application.Status newStatus) {
        appDAO.updateStatus(applicationId, newStatus);
        Application app = appDAO.findById(applicationId);
        if (app != null) {
            StudentProfile student = studentDAO.findById(app.getStudentId());
            if (student != null) {
                Notification n = new Notification();
                n.setUserId(student.getUserId());
                n.setTitle("Application Status Update: " + newStatus.name());
                n.setMessage("Your application for " + app.getJobTitle() + " at " + app.getCompanyName() +
                        " has transitioned to " + newStatus.name() + ".");
                n.setRead(false);
                notificationDAO.send(n);
            }
        }
    }

    /**
     * Schedules interview round and notifies candidate
     */
    public Interview scheduleInterview(Interview interview) {
        Interview saved = interviewDAO.save(interview);
        Application app = appDAO.findById(interview.getApplicationId());
        if (app != null) {
            appDAO.updateStatus(app.getId(), Application.Status.INTERVIEW_SCHEDULED);
            StudentProfile student = studentDAO.findById(app.getStudentId());
            if (student != null) {
                Notification n = new Notification();
                n.setUserId(student.getUserId());
                n.setTitle("Interview Scheduled: " + interview.getRoundName());
                n.setMessage("An interview has been scheduled for " + app.getJobTitle() + " (" + interview.getRoundName() +
                        ") on " + interview.getScheduledTime() + ". Link: " + interview.getMeetingLink());
                n.setRead(false);
                notificationDAO.send(n);
            }
        }
        return saved;
    }

    public List<Interview> getCompanyInterviews(int companyId) {
        return interviewDAO.findByCompanyId(companyId);
    }
}
