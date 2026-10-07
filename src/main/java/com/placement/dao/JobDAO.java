package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.CompanyProfile;
import com.placement.model.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Job & Internship DAO
 * Assigned to: Himanshu, Saurabh & Krishna (feature/database-integration, feature/recruiter-portal, feature/admin-portal)
 */
public class JobDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public List<Job> findAll() {
        List<Job> jobs = inMemory.getAllJobs();
        for (Job j : jobs) {
            CompanyProfile cp = inMemory.findCompanyById(j.getCompanyId());
            if (cp != null) {
                j.setCompanyName(cp.getCompanyName());
                j.setIndustry(cp.getIndustry());
            }
        }
        return jobs;
    }

    public List<Job> findActive() {
        List<Job> active = new ArrayList<>();
        for (Job j : findAll()) {
            if (j.getStatus() == Job.Status.ACTIVE) {
                active.add(j);
            }
        }
        return active;
    }

    public List<Job> findByCompanyId(int companyId) {
        List<Job> jobs = inMemory.getJobsByCompany(companyId);
        CompanyProfile cp = inMemory.findCompanyById(companyId);
        if (cp != null) {
            for (Job j : jobs) {
                j.setCompanyName(cp.getCompanyName());
                j.setIndustry(cp.getIndustry());
            }
        }
        return jobs;
    }

    public Job findById(int id) {
        Job j = inMemory.findJobById(id);
        if (j != null) {
            CompanyProfile cp = inMemory.findCompanyById(j.getCompanyId());
            if (cp != null) {
                j.setCompanyName(cp.getCompanyName());
                j.setIndustry(cp.getIndustry());
            }
        }
        return j;
    }

    public Job save(Job job) {
        return inMemory.saveJob(job);
    }

    public boolean delete(int id) {
        return inMemory.deleteJob(id);
    }

    public void updateStatus(int id, Job.Status status) {
        Job j = inMemory.findJobById(id);
        if (j != null) {
            j.setStatus(status);
            inMemory.saveJob(j);
        }
    }
}
