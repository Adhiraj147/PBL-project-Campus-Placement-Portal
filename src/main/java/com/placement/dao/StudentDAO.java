package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.StudentProfile;

import java.util.List;

/**
 * Student Profile DAO
 * Assigned to: Himanshu & Shlok (feature/database-integration, feature/student-portal)
 */
public class StudentDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public StudentProfile findByUserId(int userId) {
        return inMemory.findStudentByUserId(userId);
    }

    public StudentProfile findById(int id) {
        return inMemory.findStudentById(id);
    }

    public StudentProfile save(StudentProfile profile) {
        return inMemory.saveStudent(profile);
    }

    public List<StudentProfile> getAll() {
        return inMemory.getAllStudents();
    }
}
