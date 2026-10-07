package com.placement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Application Entity Model
 * Assigned to: Shlok & Saurabh (feature/student-portal, feature/recruiter-portal)
 */
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        APPLIED, SHORTLISTED, INTERVIEW_SCHEDULED, SELECTED, REJECTED
    }

    private int id;
    private int jobId;
    private int studentId;
    private Status status;
    private String coverNote;
    private Timestamp appliedAt;
    private Timestamp updatedAt;

    // Joined Fields for Job & Company
    private String jobTitle;
    private String companyName;
    private String jobType;
    private double packageLpa;
    private double stipendPm;

    // Joined Fields for Student
    private String studentName;
    private String studentEmail;
    private String rollNumber;
    private String branch;
    private double cgpa;
    private String resumeUrl;

    public Application() {}

    public Application(int id, int jobId, int studentId, Status status, String coverNote) {
        this.id = id;
        this.jobId = jobId;
        this.studentId = studentId;
        this.status = status;
        this.coverNote = coverNote;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getCoverNote() { return coverNote; }
    public void setCoverNote(String coverNote) { this.coverNote = coverNote; }

    public Timestamp getAppliedAt() { return appliedAt; }
    public void setAppliedAt(Timestamp appliedAt) { this.appliedAt = appliedAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public double getPackageLpa() { return packageLpa; }
    public void setPackageLpa(double packageLpa) { this.packageLpa = packageLpa; }

    public double getStipendPm() { return stipendPm; }
    public void setStipendPm(double stipendPm) { this.stipendPm = stipendPm; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
}
