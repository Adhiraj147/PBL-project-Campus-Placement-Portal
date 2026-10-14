package com.placement.service;

import com.placement.model.Opportunity;
import com.placement.model.Student;

/**
 * EligibilityService — Automated Candidate Qualification Engine
 * Module 5: Database Architecture, Integration Layer & System Testing
 * Author: Himanshu Yadav (XXHimanshuXX / himanshu.yadav060107@gmail.com)
 *
 * Implements 4-tier automated gatekeeping verification before candidate
 * application submissions are persisted in the database.
 */
public class EligibilityService {

    public static class EligibilityResult {
        private boolean eligible;
        private String reason;

        public EligibilityResult(boolean eligible, String reason) {
            this.eligible = eligible;
            this.reason = reason;
        }

        public boolean isEligible() { return eligible; }
        public String getReason() { return reason; }
    }

    /**
     * Evaluates a candidate student's qualification against a specific placement opportunity.
     *
     * @param student The candidate student entity with populated portfolio metrics
     * @param opp The target recruitment opportunity with criteria cutoffs
     * @return EligibilityResult containing boolean status and explanatory failure reason if ineligible
     */
    public static EligibilityResult checkEligibility(Student student, Opportunity opp) {
        // Tier 1: Check Profile Completeness (Must be 100%)
        if (!student.isProfileCompleted() || student.calculateProfileCompletion() < 100) {
            return new EligibilityResult(false, "Profile is incomplete. Please complete your profile to 100%.");
        }

        // Tier 2: Academic Merit Check (CGPA Threshold)
        if (student.getCgpa() < opp.getMinCgpa()) {
            return new EligibilityResult(false, "CGPA (" + student.getCgpa() + ") is below the required minimum (" + opp.getMinCgpa() + ").");
        }

        // Tier 3: Graduation Batch Cohort Check
        if (opp.getGraduationYearReq() > 0 && student.getGraduationYear() != opp.getGraduationYearReq()) {
            return new EligibilityResult(false, "Graduation year mismatch. Required: " + opp.getGraduationYearReq() + ", Yours: " + student.getGraduationYear() + ".");
        }

        // Tier 4: Engineering Discipline / Branch Eligibility
        String requiredBranches = opp.getEligibleBranches();
        if (requiredBranches != null && !requiredBranches.trim().isEmpty() && !requiredBranches.equalsIgnoreCase("Any")) {
            boolean branchMatch = false;
            String[] branches = requiredBranches.split(",");
            for (String b : branches) {
                if (b.trim().equalsIgnoreCase(student.getBranch())) {
                    branchMatch = true;
                    break;
                }
            }
            if (!branchMatch) {
                return new EligibilityResult(false, "Your branch (" + student.getBranch() + ") is not eligible. Required: " + requiredBranches + ".");
            }
        }

        return new EligibilityResult(true, "You are eligible to apply.");
    }
}
