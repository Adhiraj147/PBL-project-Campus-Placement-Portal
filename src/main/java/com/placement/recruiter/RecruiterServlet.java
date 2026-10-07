package com.placement.recruiter;

import com.placement.db.DBUtils;
import com.placement.model.*;
import com.placement.server.SimpleHttpRequest;
import com.placement.server.SimpleHttpResponse;
import com.placement.server.SimpleHttpServlet;
import com.placement.server.SimpleHttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Recruiter & Corporate Management Servlet
 * Module 3: Recruiter & Company Portal
 * Assigned to: Saurabh (feature/recruiter-portal)
 */
public class RecruiterServlet extends SimpleHttpServlet {
    private final RecruiterService recruiterService = new RecruiterService();

    @Override
    public void doGet(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedRecruiter(req, resp);
        if (user == null) return;

        CompanyProfile company = recruiterService.getCompanyByUserId(user.getId());
        if (company == null) {
            resp.sendError(404, "Company profile not found.");
            return;
        }

        String path = req.getPath();
        if (path.endsWith("/profile")) {
            sendCompanyProfile(company, user, resp);
        } else if (path.endsWith("/jobs")) {
            sendCompanyJobs(company.getId(), resp);
        } else if (path.endsWith("/applicants")) {
            sendJobApplicants(req, company.getId(), resp);
        } else if (path.endsWith("/interviews")) {
            sendCompanyInterviews(company.getId(), resp);
        } else {
            resp.sendError(404, "Unknown recruiter endpoint.");
        }
    }

    @Override
    public void doPost(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        User user = getAuthenticatedRecruiter(req, resp);
        if (user == null) return;

        CompanyProfile company = recruiterService.getCompanyByUserId(user.getId());
        if (company == null) {
            resp.sendError(404, "Company profile not found.");
            return;
        }

        String path = req.getPath();
        if (path.endsWith("/profile")) {
            handleUpdateProfile(req, resp, company);
        } else if (path.endsWith("/jobs/create")) {
            handleCreateJob(req, resp, company);
        } else if (path.endsWith("/jobs/delete")) {
            handleDeleteJob(req, resp, company);
        } else if (path.endsWith("/applicants/status")) {
            handleUpdateApplicantStatus(req, resp);
        } else if (path.endsWith("/interviews/schedule")) {
            handleScheduleInterview(req, resp, company);
        } else {
            resp.sendError(404, "Unknown recruiter endpoint.");
        }
    }

    private User getAuthenticatedRecruiter(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        SimpleHttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendError(401, "Authentication required.");
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user.getRole() != User.Role.RECRUITER) {
            resp.sendError(403, "Access restricted to Recruiter portal.");
            return null;
        }
        return user;
    }

    private void sendCompanyProfile(CompanyProfile cp, User u, SimpleHttpResponse resp) throws IOException {
        String json = String.format(
            "{\"id\": %d, \"companyName\": \"%s\", \"industry\": \"%s\", \"website\": \"%s\", \"location\": \"%s\", \"description\": \"%s\", \"verificationStatus\": \"%s\", \"recruiterName\": \"%s\", \"recruiterEmail\": \"%s\"}",
            cp.getId(), DBUtils.escapeJson(cp.getCompanyName()), DBUtils.escapeJson(cp.getIndustry()),
            DBUtils.escapeJson(cp.getWebsite() != null ? cp.getWebsite() : ""),
            DBUtils.escapeJson(cp.getLocation()), DBUtils.escapeJson(cp.getDescription() != null ? cp.getDescription() : ""),
            cp.getVerificationStatus().name(), DBUtils.escapeJson(u.getFullName()), DBUtils.escapeJson(u.getEmail())
        );
        resp.sendJson(json);
    }

    private void sendCompanyJobs(int companyId, SimpleHttpResponse resp) throws IOException {
        List<Job> jobs = recruiterService.getCompanyJobs(companyId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < jobs.size(); i++) {
            Job j = jobs.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(j.getId()).append(",");
            sb.append("\"title\": \"").append(DBUtils.escapeJson(j.getTitle())).append("\",");
            sb.append("\"description\": \"").append(DBUtils.escapeJson(j.getDescription())).append("\",");
            sb.append("\"jobType\": \"").append(j.getJobType().name()).append("\",");
            sb.append("\"packageLpa\": ").append(j.getPackageLpa()).append(",");
            sb.append("\"stipendPm\": ").append(j.getStipendPm()).append(",");
            sb.append("\"location\": \"").append(DBUtils.escapeJson(j.getLocation())).append("\",");
            sb.append("\"minCgpa\": ").append(j.getMinCgpa()).append(",");
            sb.append("\"eligibleBranches\": \"").append(DBUtils.escapeJson(j.getEligibleBranches())).append("\",");
            sb.append("\"deadline\": \"").append(j.getDeadline() != null ? j.getDeadline().toString() : "").append("\",");
            sb.append("\"status\": \"").append(j.getStatus().name()).append("\",");
            sb.append("\"applicantCount\": ").append(j.getApplicantCount());
            sb.append("}");
            if (i < jobs.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendJobApplicants(SimpleHttpRequest req, int companyId, SimpleHttpResponse resp) throws IOException {
        String jobIdStr = req.getParameter("jobId");
        if (jobIdStr == null) {
            resp.sendError(400, "Missing jobId");
            return;
        }
        int jobId = Integer.parseInt(jobIdStr);
        List<Application> list = recruiterService.getJobApplicants(jobId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Application a = list.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(a.getId()).append(",");
            sb.append("\"studentId\": ").append(a.getStudentId()).append(",");
            sb.append("\"studentName\": \"").append(DBUtils.escapeJson(a.getStudentName())).append("\",");
            sb.append("\"studentEmail\": \"").append(DBUtils.escapeJson(a.getStudentEmail())).append("\",");
            sb.append("\"rollNumber\": \"").append(DBUtils.escapeJson(a.getRollNumber())).append("\",");
            sb.append("\"branch\": \"").append(DBUtils.escapeJson(a.getBranch())).append("\",");
            sb.append("\"cgpa\": ").append(a.getCgpa()).append(",");
            sb.append("\"resumeUrl\": \"").append(DBUtils.escapeJson(a.getResumeUrl() != null ? a.getResumeUrl() : "")).append("\",");
            sb.append("\"status\": \"").append(a.getStatus().name()).append("\",");
            sb.append("\"coverNote\": \"").append(DBUtils.escapeJson(a.getCoverNote() != null ? a.getCoverNote() : "")).append("\",");
            sb.append("\"appliedAt\": \"").append(DBUtils.formatDateTime(a.getAppliedAt())).append("\"");
            sb.append("}");
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void sendCompanyInterviews(int companyId, SimpleHttpResponse resp) throws IOException {
        List<Interview> list = recruiterService.getCompanyInterviews(companyId);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Interview inv = list.get(i);
            sb.append("{");
            sb.append("\"id\": ").append(inv.getId()).append(",");
            sb.append("\"applicationId\": ").append(inv.getApplicationId()).append(",");
            sb.append("\"roundName\": \"").append(DBUtils.escapeJson(inv.getRoundName())).append("\",");
            sb.append("\"scheduledTime\": \"").append(DBUtils.formatDateTime(inv.getScheduledTime())).append("\",");
            sb.append("\"meetingLink\": \"").append(DBUtils.escapeJson(inv.getMeetingLink())).append("\",");
            sb.append("\"mode\": \"").append(inv.getMode().name()).append("\",");
            sb.append("\"status\": \"").append(inv.getStatus().name()).append("\",");
            sb.append("\"studentName\": \"").append(DBUtils.escapeJson(inv.getStudentName())).append("\",");
            sb.append("\"studentEmail\": \"").append(DBUtils.escapeJson(inv.getStudentEmail())).append("\",");
            sb.append("\"jobTitle\": \"").append(DBUtils.escapeJson(inv.getJobTitle())).append("\"");
            sb.append("}");
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        resp.sendJson(sb.toString());
    }

    private void handleUpdateProfile(SimpleHttpRequest req, SimpleHttpResponse resp, CompanyProfile company) throws IOException {
        String companyName = req.getParameter("companyName");
        String industry = req.getParameter("industry");
        String website = req.getParameter("website");
        String location = req.getParameter("location");
        String description = req.getParameter("description");

        if (companyName != null) company.setCompanyName(companyName);
        if (industry != null) company.setIndustry(industry);
        if (website != null) company.setWebsite(website);
        if (location != null) company.setLocation(location);
        if (description != null) company.setDescription(description);

        recruiterService.updateCompanyProfile(company);
        resp.sendJson("{\"success\": true, \"message\": \"Company profile updated.\"}");
    }

    private void handleCreateJob(SimpleHttpRequest req, SimpleHttpResponse resp, CompanyProfile company) throws IOException {
        try {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String jobTypeStr = req.getParameter("jobType");
            double packageLpa = Double.parseDouble(req.getParameter("packageLpa") != null ? req.getParameter("packageLpa") : "0");
            double stipendPm = Double.parseDouble(req.getParameter("stipendPm") != null ? req.getParameter("stipendPm") : "0");
            String location = req.getParameter("location");
            double minCgpa = Double.parseDouble(req.getParameter("minCgpa") != null ? req.getParameter("minCgpa") : "6.0");
            String branches = req.getParameter("eligibleBranches");
            String deadlineStr = req.getParameter("deadline");

            Job job = new Job();
            job.setCompanyId(company.getId());
            job.setTitle(title);
            job.setDescription(description);
            job.setJobType(Job.JobType.valueOf(jobTypeStr));
            job.setPackageLpa(packageLpa);
            job.setStipendPm(stipendPm);
            job.setLocation(location);
            job.setMinCgpa(minCgpa);
            job.setEligibleBranches(branches != null ? branches : "All Branches");
            job.setDeadline(deadlineStr != null && !deadlineStr.isEmpty() ? Date.valueOf(deadlineStr) : new Date(System.currentTimeMillis() + 30L*86400000));
            job.setStatus(Job.Status.ACTIVE);

            Job created = recruiterService.createJob(job);
            resp.sendJson(String.format("{\"success\": true, \"message\": \"Job drive created!\", \"jobId\": %d}", created.getId()));
        } catch (Exception e) {
            resp.sendError(400, "Error creating job: " + e.getMessage());
        }
    }

    private void handleDeleteJob(SimpleHttpRequest req, SimpleHttpResponse resp, CompanyProfile company) throws IOException {
        String jobIdStr = req.getParameter("jobId");
        if (jobIdStr == null) {
            resp.sendError(400, "Missing jobId");
            return;
        }
        int jobId = Integer.parseInt(jobIdStr);
        boolean ok = recruiterService.deleteJob(jobId);
        resp.sendJson(ok ? "{\"success\": true}" : "{\"error\": true, \"message\": \"Job not found\"}");
    }

    private void handleUpdateApplicantStatus(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String appIdStr = req.getParameter("applicationId");
        String statusStr = req.getParameter("status");
        if (appIdStr == null || statusStr == null) {
            resp.sendError(400, "Missing required parameters");
            return;
        }
        try {
            int appId = Integer.parseInt(appIdStr);
            Application.Status status = Application.Status.valueOf(statusStr.toUpperCase());
            recruiterService.updateApplicantStatus(appId, status);
            resp.sendJson("{\"success\": true, \"message\": \"Status updated to " + status.name() + "\"}");
        } catch (Exception e) {
            resp.sendError(400, e.getMessage());
        }
    }

    private void handleScheduleInterview(SimpleHttpRequest req, SimpleHttpResponse resp, CompanyProfile company) throws IOException {
        try {
            int appId = Integer.parseInt(req.getParameter("applicationId"));
            String roundName = req.getParameter("roundName");
            String scheduledTimeStr = req.getParameter("scheduledTime");
            String meetingLink = req.getParameter("meetingLink");
            String modeStr = req.getParameter("mode");

            Interview interview = new Interview();
            interview.setApplicationId(appId);
            interview.setRoundName(roundName);
            interview.setScheduledTime(Timestamp.valueOf(scheduledTimeStr.replace("T", " ") + ":00"));
            interview.setMeetingLink(meetingLink);
            interview.setMode(modeStr != null ? Interview.Mode.valueOf(modeStr) : Interview.Mode.ONLINE);
            interview.setStatus(Interview.Status.SCHEDULED);

            recruiterService.scheduleInterview(interview);
            resp.sendJson("{\"success\": true, \"message\": \"Interview scheduled successfully!\"}");
        } catch (Exception e) {
            resp.sendError(400, "Error scheduling interview: " + e.getMessage());
        }
    }
}
