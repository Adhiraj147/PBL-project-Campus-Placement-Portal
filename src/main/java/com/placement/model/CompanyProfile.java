package com.placement.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Company Profile Entity Model
 * Assigned to: Saurabh (feature/recruiter-portal)
 */
public class CompanyProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum VerificationStatus {
        PENDING, VERIFIED, REJECTED
    }

    private int id;
    private int userId;
    private String companyName;
    private String industry;
    private String website;
    private String location;
    private String description;
    private VerificationStatus verificationStatus;
    private Timestamp verifiedAt;

    // Joined fields from User
    private String recruiterName;
    private String recruiterEmail;
    private String recruiterPhone;

    public CompanyProfile() {}

    public CompanyProfile(int id, int userId, String companyName, String industry, String location) {
        this.id = id;
        this.userId = userId;
        this.companyName = companyName;
        this.industry = industry;
        this.location = location;
        this.verificationStatus = VerificationStatus.PENDING;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public VerificationStatus getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(VerificationStatus verificationStatus) { this.verificationStatus = verificationStatus; }

    public Timestamp getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(Timestamp verifiedAt) { this.verifiedAt = verifiedAt; }

    public String getRecruiterName() { return recruiterName; }
    public void setRecruiterName(String recruiterName) { this.recruiterName = recruiterName; }

    public String getRecruiterEmail() { return recruiterEmail; }
    public void setRecruiterEmail(String recruiterEmail) { this.recruiterEmail = recruiterEmail; }

    public String getRecruiterPhone() { return recruiterPhone; }
    public void setRecruiterPhone(String recruiterPhone) { this.recruiterPhone = recruiterPhone; }
}
