package com.placement.model;

public class Resume {

    private int resumeId;
    private int studentId;
    private String fileName;
    private String filePath;
    private String uploadedDate;

    public Resume() {
    }

    public Resume(int resumeId, int studentId, String fileName,
                  String filePath, String uploadedDate) {
        this.resumeId = resumeId;
        this.studentId = studentId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedDate = uploadedDate;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}
