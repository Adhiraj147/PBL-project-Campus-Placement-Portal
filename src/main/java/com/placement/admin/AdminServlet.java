package com.placement.admin;

import com.placement.db.DBUtils;
import com.placement.model.*;
import com.placement.server.SimpleHttpRequest;
import com.placement.server.SimpleHttpResponse;
import com.placement.server.SimpleHttpServlet;
import com.placement.server.SimpleHttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TPO Administration & Analytics Servlet
 * Module 4: Admin & Analytics
 * Assigned to: Krishna (feature/admin-portal)
 */
public class AdminServlet extends SimpleHttpServlet {
    private final AdminService adminService = new AdminService();

    @Override
    public void doGet(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedAdmin(req, resp);
        if (user == null) return;

        String path = req.getPath();
        if (path.endsWith("/stats")) {
            sendStatisticsJson(resp);
        } else if (path.endsWith("/students")) {
            sendStudentsJson(resp);
        } else if (path.endsWith("/companies")) {
            sendCompaniesJson(resp);
        } else if (path.endsWith("/jobs")) {
            sendJobsJson(resp);
        } else {
            resp.sendError(404, "Unknown admin endpoint.");
        }
    }

    @Override
    public void doPost(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedAdmin(req, resp);
        if (user == null) return;

        String path = req.getPath();
        if (path.endsWith("/verify-company")) {
            handleVerifyCompany(req, resp);
        } else if (path.endsWith("/moderate-job")) {
            handleModerateJob(req, resp);
        } else if (path.endsWith("/toggle-user")) {
            handleToggleUser(req, resp);
        } else {
            resp.sendError(404, "Unknown admin endpoint.");
        }
    }

    private User getAuthenticatedAdmin(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        SimpleHttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(401, "Authentication required.");
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user.getRole() != User.Role.ADMIN) {
            resp.sendError(403, "Access restricted to TPO Admin.");
            return null;
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    private void sendStatisticsJson(SimpleHttpResponse resp) throws IOException {
        Map<String, Object> stats = adminService.getPlacementStatistics();
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"totalStudents\": ").append(stats.get("totalStudents")).append(",");
        sb.append("\"totalCompanies\": ").append(stats.get("totalCompanies")).append(",");
        sb.append("\"totalJobs\": ").append(stats.get("totalJobs")).append(",");
        sb.append("\"totalApplications\": ").append(stats.get("totalApplications")).append(",");
        sb.append("\"placedStudents\": ").append(stats.get("placedStudents")).append(",");
        sb.append("\"placementRate\": ").append(stats.get("placementRate")).append(",");
        sb.append("\"averagePackageLpa\": ").append(stats.get("averagePackageLpa")).append(",");
        sb.append("\"highestPackageLpa\": ").append(stats.get("highestPackageLpa")).append(",");

        // branch distribution
        Map<String, Integer> totalMap = (Map<String, Integer>) stats.get("branchTotal");
        Map<String, Integer> placedMap = (Map<String, Integer>) stats.get("branchPlaced");

        sb.append("\"branchAnalytics\": [");
        int idx = 0;
        for (String branch : totalMap.keySet()) {
            int tot = totalMap.get(branch);
            int plc = placedMap.getOrDefault(branch, 0);
            sb.append("{");
            sb.append("\"branch\": \"").append(DBUtils.escapeJson(branch)).append("\",");
            sb.append("\"total\": ").append(tot).append(",");
            sb.append("\"placed\": ").append(plc);
            sb.append("}");
            if (idx++ < totalMap.size() - 1) sb.append(",");
        }
        sb.append("]");

        sb.append("}");
        resp.sendJson(sb.toString());
    }

    private void sendStudentsJson(SimpleHttpResponse resp) throws IOException {
        List<StudentProfile> students = adminService.getAllStudents();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < students.size(); i++) {
            StudentProfile s = students.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(s.getId()).append(",");
            sb.append("\"userId\": ").append(s.getUserId()).append(",");
            sb.append("\"name\": \"").append(DBUtils.escapeJson(s.getStudentName())).append("\",");
            sb.append("\"email\": \"").append(DBUtils.escapeJson(s.getStudentEmail())).append("\",");
            sb.append("\"rollNumber\": \"").append(DBUtils.escapeJson(s.getRollNumber())).append("\",");
            sb.append("\"branch\": \"").append(DBUtils.escapeJson(s.getBranch())).append("\",");
            sb.append("\"cgpa\": ").append(s.getCgpa()).append(",");
            sb.append("\"graduationYear\": ").append(s.getGraduationYear()).append(",");
            sb.append("\"skills\": \"").append(DBUtils.escapeJson(s.getSkills() != null ? s.getSkills() : "")).append("\"");
            sb.append("}");
            if (i < students.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendCompaniesJson(SimpleHttpResponse resp) throws IOException {
        List<CompanyProfile> companies = adminService.getAllCompanies();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < companies.size(); i++) {
            CompanyProfile c = companies.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(c.getId()).append(",");
            sb.append("\"companyName\": \"").append(DBUtils.escapeJson(c.getCompanyName())).append("\",");
            sb.append("\"industry\": \"").append(DBUtils.escapeJson(c.getIndustry())).append("\",");
            sb.append("\"location\": \"").append(DBUtils.escapeJson(c.getLocation())).append("\",");
            sb.append("\"website\": \"").append(DBUtils.escapeJson(c.getWebsite() != null ? c.getWebsite() : "")).append("\",");
            sb.append("\"recruiterName\": \"").append(DBUtils.escapeJson(c.getRecruiterName() != null ? c.getRecruiterName() : "")).append("\",");
            sb.append("\"verificationStatus\": \"").append(c.getVerificationStatus().name()).append("\"");
            sb.append("}");
            if (i < companies.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendJobsJson(SimpleHttpResponse resp) throws IOException {
        List<Job> jobs = adminService.getAllJobs();
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < jobs.size(); i++) {
            Job j = jobs.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(j.getId()).append(",");
            sb.append("\"title\": \"").append(DBUtils.escapeJson(j.getTitle())).append("\",");
            sb.append("\"companyName\": \"").append(DBUtils.escapeJson(j.getCompanyName())).append("\",");
            sb.append("\"jobType\": \"").append(j.getJobType().name()).append("\",");
            sb.append("\"packageLpa\": ").append(j.getPackageLpa()).append(",");
            sb.append("\"stipendPm\": ").append(j.getStipendPm()).append(",");
            sb.append("\"location\": \"").append(DBUtils.escapeJson(j.getLocation())).append("\",");
            sb.append("\"minCgpa\": ").append(j.getMinCgpa()).append(",");
            sb.append("\"status\": \"").append(j.getStatus().name()).append("\",");
            sb.append("\"applicantCount\": ").append(j.getApplicantCount());
            sb.append("}");
            if (i < jobs.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void handleVerifyCompany(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String companyIdStr = req.getParameter("companyId");
        String approveStr = req.getParameter("approve");
        if (companyIdStr == null) {
            resp.sendError(400, "Missing companyId");
            return;
        }
        int companyId = Integer.parseInt(companyIdStr);
        boolean approve = Boolean.parseBoolean(approveStr);
        adminService.verifyCompany(companyId, approve);
        resp.sendJson("{\"success\": true, \"message\": \"Company verification status updated.\"}");
    }

    private void handleModerateJob(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String jobIdStr = req.getParameter("jobId");
        String approveStr = req.getParameter("approve");
        if (jobIdStr == null) {
            resp.sendError(400, "Missing jobId");
            return;
        }
        int jobId = Integer.parseInt(jobIdStr);
        boolean approve = Boolean.parseBoolean(approveStr);
        adminService.moderateJob(jobId, approve);
        resp.sendJson("{\"success\": true, \"message\": \"Job drive status updated.\"}");
    }

    private void handleToggleUser(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String userIdStr = req.getParameter("userId");
        String statusStr = req.getParameter("status");
        if (userIdStr == null || statusStr == null) {
            resp.sendError(400, "Missing required parameters");
            return;
        }
        int userId = Integer.parseInt(userIdStr);
        User.Status status = User.Status.valueOf(statusStr.toUpperCase());
        adminService.updateUserStatus(userId, status);
        resp.sendJson("{\"success\": true, \"message\": \"User status updated to " + status.name() + "\"}");
    }
}
