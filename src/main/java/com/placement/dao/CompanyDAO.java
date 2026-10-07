package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.CompanyProfile;

import java.sql.Timestamp;
import java.util.List;

/**
 * Company Profile DAO
 * Assigned to: Himanshu & Saurabh (feature/database-integration, feature/recruiter-portal)
 */
public class CompanyDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public CompanyProfile findByUserId(int userId) {
        return inMemory.findCompanyByUserId(userId);
    }

    public CompanyProfile findById(int id) {
        return inMemory.findCompanyById(id);
    }

    public CompanyProfile save(CompanyProfile profile) {
        return inMemory.saveCompany(profile);
    }

    public List<CompanyProfile> getAll() {
        return inMemory.getAllCompanies();
    }

    public void updateStatus(int companyId, CompanyProfile.VerificationStatus status) {
        CompanyProfile cp = inMemory.findCompanyById(companyId);
        if (cp != null) {
            cp.setVerificationStatus(status);
            if (status == CompanyProfile.VerificationStatus.VERIFIED) {
                cp.setVerifiedAt(new Timestamp(System.currentTimeMillis()));
            }
            inMemory.saveCompany(cp);
        }
    }
}
