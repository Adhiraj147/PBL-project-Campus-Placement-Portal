package com.placement.model;

public class Eligibility {

    private int studentId;
    private int jobId;
    private double studentCgpa;
    private double requiredCgpa;
    private String requiredSkills;
    private String studentSkills;
    private boolean cgpaEligible;
    private boolean skillsEligible;
    private boolean eligible;

    public Eligibility() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public double getStudentCgpa() {
        return studentCgpa;
    }

    public void setStudentCgpa(double studentCgpa) {
        this.studentCgpa = studentCgpa;
    }

    public double getRequiredCgpa() {
        return requiredCgpa;
    }

    public void setRequiredCgpa(double requiredCgpa) {
        this.requiredCgpa = requiredCgpa;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getStudentSkills() {
        return studentSkills;
    }

    public void setStudentSkills(String studentSkills) {
        this.studentSkills = studentSkills;
    }

    public boolean isCgpaEligible() {
        return cgpaEligible;
    }

    public void setCgpaEligible(boolean cgpaEligible) {
        this.cgpaEligible = cgpaEligible;
    }

    public boolean isSkillsEligible() {
        return skillsEligible;
    }

    public void setSkillsEligible(boolean skillsEligible) {
        this.skillsEligible = skillsEligible;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
}
