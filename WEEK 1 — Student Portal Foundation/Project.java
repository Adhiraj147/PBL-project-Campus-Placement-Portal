package com.placement.model;

public class Project {

    private int projectId;
    private int studentId;

    private String title;
    private String description;
    private String link;

    public Project() {
    }

    // Project ID
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // Student ID
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    // Project Title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Project Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Project Link
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
