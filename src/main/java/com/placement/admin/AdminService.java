package com.placement.admin;

import com.placement.dao.*;
import com.placement.model.*;

import java.util.*;

/**
 * TPO Administration & Analytics Engine
 * Module 4: Admin & Analytics
 * Assigned to: Krishna (feature/admin-portal)
 */
public class AdminService {
    private final UserDAO userDAO = new UserDAO();
    private final StudentDAO studentDAO = new StudentDAO();
    private final CompanyDAO companyDAO = new CompanyDAO();
    private final JobDAO jobDAO = new JobDAO();
    private final ApplicationDAO appDAO = new ApplicationDAO();

    /**
     * Aggregates live placement analytics and KPIs for university leadership
     */
    public Map<String, Object> getPlacementStatistics() {
        Map<String, Object> stats = new HashMap<>();

        List<StudentProfile> students = studentDAO.getAll();
        List<CompanyProfile> companies = companyDAO.getAll();
        List<Job> jobs = jobDAO.findAll();
        List<Application> apps = appDAO.findAll();

        int totalStudents = students.size();
        int totalCompanies = companies.size();
        int totalJobs = jobs.size();
        int totalApplications = apps.size();

        Set<Integer> placedStudentIds = new HashSet<>();
        double totalPackage = 0.0;
        int placedCount = 0;
        double highestPackage = 0.0;

        for (Application a : apps) {
            if (a.getStatus() == Application.Status.SELECTED) {
                placedStudentIds.add(a.getStudentId());
                if (a.getPackageLpa() > 0) {
                    totalPackage += a.getPackageLpa();
                    placedCount++;
                    if (a.getPackageLpa() > highestPackage) {
                        highestPackage = a.getPackageLpa();
                    }
                }
            }
        }

        double placementRate = totalStudents > 0 ? ((double) placedStudentIds.size() / totalStudents) * 100.0 : 0.0;
        double averagePackage = placedCount > 0 ? (totalPackage / placedCount) : 0.0;

        stats.put("totalStudents", totalStudents);
        stats.put("totalCompanies", totalCompanies);
        stats.put("totalJobs", totalJobs);
        stats.put("totalApplications", totalApplications);
        stats.put("placedStudents", placedStudentIds.size());
        stats.put("placementRate", Math.round(placementRate * 10.0) / 10.0);
        stats.put("averagePackageLpa", Math.round(averagePackage * 10.0) / 10.0);
        stats.put("highestPackageLpa", Math.round(highestPackage * 10.0) / 10.0);

        // Branch-wise placement distribution
        Map<String, Integer> branchTotal = new HashMap<>();
        Map<String, Integer> branchPlaced = new HashMap<>();

        for (StudentProfile sp : students) {
            branchTotal.put(sp.getBranch(), branchTotal.getOrDefault(sp.getBranch(), 0) + 1);
            if (placedStudentIds.contains(sp.getId())) {
                branchPlaced.put(sp.getBranch(), branchPlaced.getOrDefault(sp.getBranch(), 0) + 1);
            }
        }

        stats.put("branchTotal", branchTotal);
        stats.put("branchPlaced", branchPlaced);

        return stats;
    }

    public List<StudentProfile> getAllStudents() {
        return studentDAO.getAll();
    }

    public List<CompanyProfile> getAllCompanies() {
        return companyDAO.getAll();
    }

    public List<Job> getAllJobs() {
        return jobDAO.findAll();
    }

    public void verifyCompany(int companyId, boolean approve) {
        CompanyProfile.VerificationStatus status = approve ? 
                CompanyProfile.VerificationStatus.VERIFIED : CompanyProfile.VerificationStatus.REJECTED;
        companyDAO.updateStatus(companyId, status);
    }

    public void moderateJob(int jobId, boolean approve) {
        Job.Status status = approve ? Job.Status.ACTIVE : Job.Status.CLOSED;
        jobDAO.updateStatus(jobId, status);
    }

    public void updateUserStatus(int userId, User.Status status) {
        User u = userDAO.findById(userId);
        if (u != null) {
            u.setStatus(status);
            userDAO.updateUser(u);
        }
    }
}
