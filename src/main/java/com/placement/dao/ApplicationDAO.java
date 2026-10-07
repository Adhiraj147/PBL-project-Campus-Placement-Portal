package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.Application;
import com.placement.model.CompanyProfile;
import com.placement.model.Job;
import com.placement.model.StudentProfile;

import java.util.List;

/**
 * Application DAO
 * Assigned to: Himanshu, Shlok & Saurabh (feature/database-integration, feature/student-portal, feature/recruiter-portal)
 */
public class ApplicationDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public List<Application> findByStudentId(int studentId) {
        List<Application> apps = inMemory.getApplicationsByStudent(studentId);
        populateJoins(apps);
        return apps;
    }

    public List<Application> findByJobId(int jobId) {
        List<Application> apps = inMemory.getApplicationsByJob(jobId);
        populateJoins(apps);
        return apps;
    }

    public List<Application> findAll() {
        List<Application> apps = inMemory.getAllApplications();
        populateJoins(apps);
        return apps;
    }

    public Application findById(int id) {
        Application a = inMemory.findApplicationById(id);
        if (a != null) populateJoin(a);
        return a;
    }

    public Application find(int studentId, int jobId) {
        Application a = inMemory.findApplication(studentId, jobId);
        if (a != null) populateJoin(a);
        return a;
    }

    public Application save(Application app) {
        return inMemory.saveApplication(app);
    }

    public void updateStatus(int id, Application.Status status) {
        Application a = inMemory.findApplicationById(id);
        if (a != null) {
            a.setStatus(status);
            inMemory.saveApplication(a);
        }
    }

    public boolean delete(int id) {
        return inMemory.deleteApplication(id);
    }

    private void populateJoins(List<Application> apps) {
        for (Application a : apps) {
            populateJoin(a);
        }
    }

    private void populateJoin(Application a) {
        Job j = inMemory.findJobById(a.getJobId());
        if (j != null) {
            a.setJobTitle(j.getTitle());
            a.setJobType(j.getJobType().name());
            a.setPackageLpa(j.getPackageLpa());
            a.setStipendPm(j.getStipendPm());
            CompanyProfile cp = inMemory.findCompanyById(j.getCompanyId());
            if (cp != null) {
                a.setCompanyName(cp.getCompanyName());
            }
        }
        StudentProfile sp = inMemory.findStudentById(a.getStudentId());
        if (sp != null) {
            a.setStudentName(sp.getStudentName());
            a.setStudentEmail(sp.getStudentEmail());
            a.setRollNumber(sp.getRollNumber());
            a.setBranch(sp.getBranch());
            a.setCgpa(sp.getCgpa());
            a.setResumeUrl(sp.getResumeUrl());
        }
    }
}
