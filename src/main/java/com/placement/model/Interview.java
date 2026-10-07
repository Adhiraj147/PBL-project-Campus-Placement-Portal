package com.placement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Interview Entity Model
 * Assigned to: Saurabh & Himanshu (feature/recruiter-portal, feature/database-integration)
 */
public class Interview implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Mode {
        ONLINE, IN_PERSON
    }

    public enum Status {
        SCHEDULED, COMPLETED, CANCELLED
    }

    private int id;
    private int applicationId;
    private String roundName;
    private Timestamp scheduledTime;
    private String meetingLink;
    private Mode mode;
    private Status status;
    private String feedback;
    private Timestamp createdAt;

    // Joined Fields
    private String studentName;
    private String studentEmail;
    private String jobTitle;
    private String companyName;

    public Interview() {}

    public Interview(int id, int applicationId, String roundName, Timestamp scheduledTime, String meetingLink, Mode mode, Status status) {
        this.id = id;
        this.applicationId = applicationId;
        this.roundName = roundName;
        this.scheduledTime = scheduledTime;
        this.meetingLink = meetingLink;
        this.mode = mode;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getApplicationId() { return applicationId; }
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }

    public String getRoundName() { return roundName; }
    public void setRoundName(String roundName) { this.roundName = roundName; }

    public Timestamp getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(Timestamp scheduledTime) { this.scheduledTime = scheduledTime; }

    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }

    public Mode getMode() { return mode; }
    public void setMode(Mode mode) { this.mode = mode; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
