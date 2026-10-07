package com.placement.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Job & Internship Entity Model
 * Assigned to: Saurabh & Krishna (feature/recruiter-portal, feature/admin-portal)
 */
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum JobType {
        FULL_TIME, INTERNSHIP
    }

    public enum Status {
        PENDING_APPROVAL, ACTIVE, CLOSED
    }

    private int id;
    private int companyId;
    private String title;
    private String description;
    private JobType jobType;
    private double packageLpa;
    private double stipendPm;
    private String location;
    private double minCgpa;
    private String eligibleBranches;
    private Date deadline;
    private Status status;
    private Timestamp createdAt;

    // Joined fields from CompanyProfile
    private String companyName;
    private String industry;
    private int applicantCount;
    private boolean isEligible; // Computed helper for current student view

    public Job() {}

    public Job(int id, int companyId, String title, JobType jobType, double packageLpa, double stipendPm, 
               String location, double minCgpa, String eligibleBranches, Date deadline, Status status) {
        this.id = id;
        this.companyId = companyId;
        this.title = title;
        this.jobType = jobType;
        this.packageLpa = packageLpa;
        this.stipendPm = stipendPm;
        this.location = location;
        this.minCgpa = minCgpa;
        this.eligibleBranches = eligibleBranches;
        this.deadline = deadline;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }

    public double getPackageLpa() { return packageLpa; }
    public void setPackageLpa(double packageLpa) { this.packageLpa = packageLpa; }

    public double getStipendPm() { return stipendPm; }
    public void setStipendPm(double stipendPm) { this.stipendPm = stipendPm; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getMinCgpa() { return minCgpa; }
    public void setMinCgpa(double minCgpa) { this.minCgpa = minCgpa; }

    public String getEligibleBranches() { return eligibleBranches; }
    public void setEligibleBranches(String eligibleBranches) { this.eligibleBranches = eligibleBranches; }

    public Date getDeadline() { return deadline; }
    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public int getApplicantCount() { return applicantCount; }
    public void setApplicantCount(int applicantCount) { this.applicantCount = applicantCount; }

    public boolean isEligible() { return isEligible; }
    public void setEligible(boolean eligible) { isEligible = eligible; }
}
