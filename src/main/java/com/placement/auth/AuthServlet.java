package com.placement.auth;

import com.placement.model.User;
import com.placement.server.SimpleHttpRequest;
import com.placement.server.SimpleHttpResponse;
import com.placement.server.SimpleHttpServlet;
import com.placement.server.SimpleHttpSession;

import java.io.IOException;

/**
 * Authentication & Session Controller Servlet
 * Module 1: Authentication & User Management
 * Assigned to: Adhiraj (feature/authentication)
 */
public class AuthServlet extends SimpleHttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    public void doGet(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String path = req.getPath();
        if (path.endsWith("/me")) {
            handleGetCurrentUser(req, resp);
        } else if (path.endsWith("/logout")) {
            handleLogout(req, resp);
        } else {
            resp.sendError(404, "Endpoint not found");
        }
    }

    @Override
    public void doPost(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String path = req.getPath();
        if (path.endsWith("/login")) {
            handleLogin(req, resp);
        } else if (path.endsWith("/register")) {
            handleRegister(req, resp);
        } else if (path.endsWith("/logout")) {
            handleLogout(req, resp);
        } else {
            resp.sendError(404, "Endpoint not found");
        }
    }

    private void handleLogin(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            resp.sendError(400, "Email and password are required.");
            return;
        }

        try {
            User user = authService.login(email, password);
            if (user == null) {
                resp.sendError(401, "Invalid email or password.");
                return;
            }

            SimpleHttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            resp.addCookie("JSESSIONID", session.getId(), "/", 86400);

            String redirectUrl = switch (user.getRole()) {
                case STUDENT -> "/student-dashboard.html";
                case RECRUITER -> "/recruiter-dashboard.html";
                case ADMIN -> "/admin-dashboard.html";
            };

            String json = String.format(
                "{\"success\": true, \"redirect\": \"%s\", \"user\": {\"id\": %d, \"name\": \"%s\", \"email\": \"%s\", \"role\": \"%s\"}}",
                redirectUrl, user.getId(), user.getFullName().replace("\"", "\\\""), user.getEmail(), user.getRole().name()
            );
            resp.sendJson(json);
        } catch (IllegalStateException e) {
            resp.sendError(403, e.getMessage());
        }
    }

    private void handleRegister(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String roleStr = req.getParameter("role");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");

        if (email == null || password == null || fullName == null || roleStr == null) {
            resp.sendError(400, "All primary fields are required.");
            return;
        }

        try {
            User user;
            if ("STUDENT".equalsIgnoreCase(roleStr)) {
                String rollNumber = req.getParameter("rollNumber");
                String branch = req.getParameter("branch");
                double cgpa = Double.parseDouble(req.getParameter("cgpa") != null ? req.getParameter("cgpa") : "7.0");
                int gradYear = Integer.parseInt(req.getParameter("gradYear") != null ? req.getParameter("gradYear") : "2026");
                user = authService.registerStudent(email, password, fullName, phone, rollNumber, branch, cgpa, gradYear);
            } else if ("RECRUITER".equalsIgnoreCase(roleStr)) {
                String companyName = req.getParameter("companyName");
                String industry = req.getParameter("industry");
                String location = req.getParameter("location");
                String website = req.getParameter("website");
                user = authService.registerRecruiter(email, password, fullName, phone, companyName, industry, location, website);
            } else {
                resp.sendError(400, "Unsupported registration role: " + roleStr);
                return;
            }

            SimpleHttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            resp.addCookie("JSESSIONID", session.getId(), "/", 86400);

            String redirectUrl = user.getRole() == User.Role.STUDENT ? "/student-dashboard.html" : "/recruiter-dashboard.html";
            String json = String.format(
                "{\"success\": true, \"redirect\": \"%s\", \"message\": \"Registration successful\", \"user\": {\"id\": %d, \"name\": \"%s\", \"role\": \"%s\"}}",
                redirectUrl, user.getId(), user.getFullName().replace("\"", "\\\""), user.getRole().name()
            );
            resp.sendJson(json);
        } catch (Exception e) {
            resp.sendError(400, e.getMessage());
        }
    }

    private void handleGetCurrentUser(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        SimpleHttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                String json = String.format(
                    "{\"authenticated\": true, \"user\": {\"id\": %d, \"name\": \"%s\", \"email\": \"%s\", \"role\": \"%s\", \"phone\": \"%s\"}}",
                    user.getId(), user.getFullName().replace("\"", "\\\""), user.getEmail(), user.getRole().name(),
                    user.getPhone() != null ? user.getPhone() : ""
                );
                resp.sendJson(json);
                return;
            }
        }
        resp.sendJson("{\"authenticated\": false}");
    }

    private void handleLogout(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        SimpleHttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.addCookie("JSESSIONID", "", "/", 0);
        resp.sendJson("{\"success\": true, \"redirect\": \"/login.html\"}");
    }
}
