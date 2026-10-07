package com.placement.student;

import com.placement.db.DBUtils;
import com.placement.model.*;
import com.placement.server.SimpleHttpRequest;
import com.placement.server.SimpleHttpResponse;
import com.placement.server.SimpleHttpServlet;
import com.placement.server.SimpleHttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Student Portal Controller Servlet
 * Module 2: Student Portal
 * Assigned to: Shlok (feature/student-portal)
 */
public class StudentServlet extends SimpleHttpServlet {
    private final StudentService studentService = new StudentService();

    @Override
    public void doGet(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedStudent(req, resp);
        if (user == null) return;

        StudentProfile profile = studentService.getProfileByUserId(user.getId());
        if (profile == null) {
            resp.sendError(404, "Student profile record not found.");
            return;
        }

        String path = req.getPath();
        if (path.endsWith("/profile")) {
            sendProfileJson(profile, user, resp);
        } else if (path.endsWith("/jobs")) {
            sendJobsJson(profile.getId(), resp);
        } else if (path.endsWith("/applications")) {
            sendApplicationsJson(profile.getId(), resp);
        } else if (path.endsWith("/interviews")) {
            sendInterviewsJson(profile.getId(), resp);
        } else if (path.endsWith("/notifications")) {
            sendNotificationsJson(user.getId(), resp);
        } else {
            resp.sendError(404, "Unknown student endpoint.");
        }
    }

    @Override
    public void doPost(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedStudent(req, resp);
        if (user == null) return;

        StudentProfile profile = studentService.getProfileByUserId(user.getId());
        if (profile == null) {
            resp.sendError(404, "Student profile record not found.");
            return;
        }

        String path = req.getPath();
        if (path.endsWith("/profile")) {
            handleUpdateProfile(req, resp, profile);
        } else if (path.endsWith("/apply")) {
            handleApply(req, resp, profile);
        } else if (path.endsWith("/withdraw")) {
            handleWithdraw(req, resp, profile);
        } else if (path.endsWith("/notifications/read")) {
            studentService.markNotificationsRead(user.getId());
            resp.sendJson("{\"success\": true}");
        } else {
            resp.sendError(404, "Unknown student endpoint.");
        }
    }

    private User getAuthenticatedStudent(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        SimpleHttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(401, "Authentication required.");
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user.getRole() != User.Role.STUDENT) {
            resp.sendError(403, "Access restricted to Student portal.");
            return null;
        }
        return user;
    }

    private void sendProfileJson(StudentProfile p, User u, SimpleHttpResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": ").append(p.getId()).append(",");
        sb.append("\"userId\": ").append(p.getUserId()).append(",");
        sb.append("\"fullName\": \"").append(DBUtils.escapeJson(u.getFullName())).append("\",");
        sb.append("\"email\": \"").append(DBUtils.escapeJson(u.getEmail())).append("\",");
        sb.append("\"phone\": \"").append(DBUtils.escapeJson(u.getPhone() != null ? u.getPhone() : "")).append("\",");
        sb.append("\"rollNumber\": \"").append(DBUtils.escapeJson(p.getRollNumber())).append("\",");
        sb.append("\"branch\": \"").append(DBUtils.escapeJson(p.getBranch())).append("\",");
        sb.append("\"cgpa\": ").append(p.getCgpa()).append(",");
        sb.append("\"graduationYear\": ").append(p.getGraduationYear()).append(",");
        sb.append("\"resumeUrl\": \"").append(DBUtils.escapeJson(p.getResumeUrl() != null ? p.getResumeUrl() : "")).append("\",");
        sb.append("\"skills\": \"").append(DBUtils.escapeJson(p.getSkills() != null ? p.getSkills() : "")).append("\",");
        sb.append("\"bio\": \"").append(DBUtils.escapeJson(p.getBio() != null ? p.getBio() : "")).append("\",");
        sb.append("\"linkedinUrl\": \"").append(DBUtils.escapeJson(p.getLinkedinUrl() != null ? p.getLinkedinUrl() : "")).append("\",");
        sb.append("\"githubUrl\": \"").append(DBUtils.escapeJson(p.getGithubUrl() != null ? p.getGithubUrl() : "")).append("\"");
        sb.append("}");
        resp.sendJson(sb.toString());
    }

    private void sendJobsJson(int studentId, SimpleHttpResponse resp) throws IOException {
        List<Job> jobs = studentService.getEligibleJobsForStudent(studentId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < jobs.size(); i++) {
            Job j = jobs.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(j.getId()).append(",");
            sb.append("\"title\": \"").append(DBUtils.escapeJson(j.getTitle())).append("\",");
            sb.append("\"companyName\": \"").append(DBUtils.escapeJson(j.getCompanyName())).append("\",");
            sb.append("\"industry\": \"").append(DBUtils.escapeJson(j.getIndustry())).append("\",");
            sb.append("\"description\": \"").append(DBUtils.escapeJson(j.getDescription())).append("\",");
            sb.append("\"jobType\": \"").append(j.getJobType().name()).append("\",");
            sb.append("\"packageLpa\": ").append(j.getPackageLpa()).append(",");
            sb.append("\"stipendPm\": ").append(j.getStipendPm()).append(",");
            sb.append("\"location\": \"").append(DBUtils.escapeJson(j.getLocation())).append("\",");
            sb.append("\"minCgpa\": ").append(j.getMinCgpa()).append(",");
            sb.append("\"eligibleBranches\": \"").append(DBUtils.escapeJson(j.getEligibleBranches())).append("\",");
            sb.append("\"deadline\": \"").append(j.getDeadline() != null ? j.getDeadline().toString() : "").append("\",");
            sb.append("\"applicantCount\": ").append(j.getApplicantCount()).append(",");
            sb.append("\"isEligible\": ").append(j.isEligible());
            sb.append("}");
            if (i < jobs.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendApplicationsJson(int studentId, SimpleHttpResponse resp) throws IOException {
        List<Application> apps = studentService.getStudentApplications(studentId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < apps.size(); i++) {
            Application a = apps.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(a.getId()).append(",");
            sb.append("\"jobId\": ").append(a.getJobId()).append(",");
            sb.append("\"jobTitle\": \"").append(DBUtils.escapeJson(a.getJobTitle())).append("\",");
            sb.append("\"companyName\": \"").append(DBUtils.escapeJson(a.getCompanyName())).append("\",");
            sb.append("\"jobType\": \"").append(a.getJobType()).append("\",");
            sb.append("\"packageLpa\": ").append(a.getPackageLpa()).append(",");
            sb.append("\"status\": \"").append(a.getStatus().name()).append("\",");
            sb.append("\"coverNote\": \"").append(DBUtils.escapeJson(a.getCoverNote() != null ? a.getCoverNote() : "")).append("\",");
            sb.append("\"appliedAt\": \"").append(DBUtils.formatDateTime(a.getAppliedAt())).append("\"");
            sb.append("}");
            if (i < apps.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendInterviewsJson(int studentId, SimpleHttpResponse resp) throws IOException {
        List<Interview> interviews = studentService.getStudentInterviews(studentId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < interviews.size(); i++) {
            Interview inv = interviews.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(inv.getId()).append(",");
            sb.append("\"roundName\": \"").append(DBUtils.escapeJson(inv.getRoundName())).append("\",");
            sb.append("\"scheduledTime\": \"").append(DBUtils.formatDateTime(inv.getScheduledTime())).append("\",");
            sb.append("\"meetingLink\": \"").append(DBUtils.escapeJson(inv.getMeetingLink())).append("\",");
            sb.append("\"mode\": \"").append(inv.getMode().name()).append("\",");
            sb.append("\"status\": \"").append(inv.getStatus().name()).append("\",");
            sb.append("\"jobTitle\": \"").append(DBUtils.escapeJson(inv.getJobTitle())).append("\",");
            sb.append("\"companyName\": \"").append(DBUtils.escapeJson(inv.getCompanyName())).append("\",");
            sb.append("\"feedback\": \"").append(DBUtils.escapeJson(inv.getFeedback() != null ? inv.getFeedback() : "")).append("\"");
            sb.append("}");
            if (i < interviews.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendNotificationsJson(int userId, SimpleHttpResponse resp) throws IOException {
        List<Notification> list = studentService.getStudentNotifications(userId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Notification n = list.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(n.getId()).append(",");
            sb.append("\"title\": \"").append(DBUtils.escapeJson(n.getTitle())).append("\",");
            sb.append("\"message\": \"").append(DBUtils.escapeJson(n.getMessage())).append("\",");
            sb.append("\"isRead\": ").append(n.isRead()).append(",");
            sb.append("\"createdAt\": \"").append(DBUtils.formatDateTime(n.getCreatedAt())).append("\"");
            sb.append("}");
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void handleUpdateProfile(SimpleHttpRequest req, SimpleHttpResponse resp, StudentProfile profile) throws IOException {
        String skills = req.getParameter("skills");
        String bio = req.getParameter("bio");
        String resumeUrl = req.getParameter("resumeUrl");
        String linkedinUrl = req.getParameter("linkedinUrl");
        String githubUrl = req.getParameter("githubUrl");

        if (skills != null) profile.setSkills(skills);
        if (bio != null) profile.setBio(bio);
        if (resumeUrl != null) profile.setResumeUrl(resumeUrl);
        if (linkedinUrl != null) profile.setLinkedinUrl(linkedinUrl);
        if (githubUrl != null) profile.setGithubUrl(githubUrl);

        studentService.updateProfile(profile);
        resp.sendJson("{\"success\": true, \"message\": \"Profile updated successfully!\"}");
    }

    private void handleApply(SimpleHttpRequest req, SimpleHttpResponse resp, StudentProfile profile) throws IOException {
        String jobIdStr = req.getParameter("jobId");
        String coverNote = req.getParameter("coverNote");

        if (jobIdStr == null) {
            resp.sendError(400, "Job ID parameter missing.");
            return;
        }

        try {
            int jobId = Integer.parseInt(jobIdStr);
            Application app = studentService.applyForJob(profile.getId(), jobId, coverNote);
            resp.sendJson(String.format("{\"success\": true, \"message\": \"Application submitted!\", \"applicationId\": %d}", app.getId()));
        } catch (Exception e) {
            resp.sendError(400, e.getMessage());
        }
    }

    private void handleWithdraw(SimpleHttpRequest req, SimpleHttpResponse resp, StudentProfile profile) throws IOException {
        String appIdStr = req.getParameter("applicationId");
        if (appIdStr == null) {
            resp.sendError(400, "Application ID missing.");
            return;
        }
        try {
            int appId = Integer.parseInt(appIdStr);
            boolean ok = studentService.withdrawApplication(appId, profile.getId());
            if (ok) {
                resp.sendJson("{\"success\": true, \"message\": \"Application withdrawn successfully.\"}");
            } else {
                resp.sendError(400, "Could not withdraw application.");
            }
        } catch (Exception e) {
            resp.sendError(400, e.getMessage());
        }
    }
}
