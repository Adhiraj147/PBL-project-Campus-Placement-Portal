package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.*;

import java.util.List;

/**
 * Interview DAO
 * Assigned to: Himanshu & Saurabh (feature/database-integration, feature/recruiter-portal)
 */
public class InterviewDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public List<Interview> findAll() {
        List<Interview> list = inMemory.getAllInterviews();
        populateJoins(list);
        return list;
    }

    public List<Interview> findByStudentId(int studentId) {
        List<Interview> list = inMemory.getInterviewsByStudent(studentId);
        populateJoins(list);
        return list;
    }

    public List<Interview> findByCompanyId(int companyId) {
        List<Interview> list = inMemory.getInterviewsByCompany(companyId);
        populateJoins(list);
        return list;
    }

    public Interview save(Interview interview) {
        return inMemory.saveInterview(interview);
    }

    public void updateStatus(int id, Interview.Status status, String feedback) {
        for (Interview i : inMemory.getAllInterviews()) {
            if (i.getId() == id) {
                i.setStatus(status);
                if (feedback != null) i.setFeedback(feedback);
                inMemory.saveInterview(i);
                break;
            }
        }
    }

    private void populateJoins(List<Interview> list) {
        for (Interview i : list) {
            Application a = inMemory.findApplicationById(i.getApplicationId());
            if (a != null) {
                StudentProfile sp = inMemory.findStudentById(a.getStudentId());
                if (sp != null) {
                    i.setStudentName(sp.getStudentName());
                    i.setStudentEmail(sp.getStudentEmail());
                }
                Job j = inMemory.findJobById(a.getJobId());
                if (j != null) {
                    i.setJobTitle(j.getTitle());
                    CompanyProfile cp = inMemory.findCompanyById(j.getCompanyId());
                    if (cp != null) {
                        i.setCompanyName(cp.getCompanyName());
                    }
                }
            }
        }
    }
}
