package com.placement.service;

import com.placement.model.Opportunity;
import com.placement.model.Student;

/**
 * ============================================================================
 * CAMPUS PLACEMENT AND INTERNSHIP PORTAL
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX | himanshu.yadav060107@gmail.com)
 * ============================================================================
 * Business Service Layer for Automated Candidate Eligibility Verification.
 * Prevents non-compliant students from applying to recruitment drives.
 */
public class EligibilityService {

    /**
     * Value object encapsulating eligibility determination and human-readable explanation.
     */
    public static class EligibilityResult {
        private final boolean eligible;
        private final String reason;

        public EligibilityResult(boolean eligible, String reason) {
            this.eligible = eligible;
            this.reason = reason;
        }

        public boolean isEligible() { return eligible; }
        public String getReason() { return reason; }
    }

    /**
     * Validates candidate against company criteria rules.
     * Evaluates:
     *  1. Profile Completeness (must be 100%)
     *  2. CGPA threshold (student.cgpa >= opp.minCgpa)
     *  3. Graduation Batch Year (student.graduationYear == opp.graduationYearReq)
     *  4. Department / Branch eligibility (case-insensitive csv match or "Any")
     * 
     * @param student The candidate student profile
     * @param opp The targeted job or internship opportunity
     * @return EligibilityResult containing boolean decision and descriptive message
     */
    public static EligibilityResult checkEligibility(Student student, Opportunity opp) {
        if (student == null) {
            return new EligibilityResult(false, "Student record not found. Please log in again.");
        }
        if (opp == null) {
            return new EligibilityResult(false, "Opportunity not found or expired.");
        }

        // Rule 1: Check Profile Completeness
        if (!student.isProfileCompleted() || student.calculateProfileCompletion() < 100) {
            return new EligibilityResult(false, "Profile is incomplete (" + student.calculateProfileCompletion() + "%). Please complete your profile to 100% before applying.");
        }

        // Rule 2: Check CGPA Cutoff
        if (student.getCgpa() < opp.getMinCgpa()) {
            return new EligibilityResult(false, "CGPA (" + student.getCgpa() + ") is below the required minimum (" + opp.getMinCgpa() + ").");
        }

        // Rule 3: Check Graduation Year
        if (opp.getGraduationYearReq() > 0 && student.getGraduationYear() != opp.getGraduationYearReq()) {
            return new EligibilityResult(false, "Graduation year mismatch. Required: " + opp.getGraduationYearReq() + ", Yours: " + student.getGraduationYear() + ".");
        }

        // Rule 4: Check Branch / Department Eligibility
        String requiredBranches = opp.getEligibleBranches();
        if (requiredBranches != null && !requiredBranches.trim().isEmpty() && !requiredBranches.equalsIgnoreCase("Any")) {
            boolean branchMatch = false;
            String[] branches = requiredBranches.split(",");
            for (String b : branches) {
                if (student.getBranch() != null && b.trim().equalsIgnoreCase(student.getBranch().trim())) {
                    branchMatch = true;
                    break;
                }
            }
            if (!branchMatch) {
                return new EligibilityResult(false, "Your branch (" + student.getBranch() + ") is not eligible. Required branches: " + requiredBranches + ".");
            }
        }

        return new EligibilityResult(true, "You are eligible to apply.");
    }
}
