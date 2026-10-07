package com.placement.server;

import com.placement.auth.AuthServlet;
import com.placement.student.StudentServlet;
import com.placement.recruiter.RecruiterServlet;
import com.placement.admin.AdminServlet;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Pure Java Embedded HTTP Application Server
 * Runs out of the box with standard JDK runtime (No external Tomcat/Maven installation required).
 * Handles Static Assets, Session Management, Security Context, and RESTful Servlets.
 * Assigned to: Himanshu (feature/database-integration)
 */
public class PlacementServer {
    private static final int DEFAULT_PORT = 8080;
    private static final Map<String, SimpleHttpSession> SESSIONS = new ConcurrentHashMap<>();

    private final int port;
    private HttpServer server;

    private final AuthServlet authServlet = new AuthServlet();
    private final StudentServlet studentServlet = new StudentServlet();
    private final RecruiterServlet recruiterServlet = new RecruiterServlet();
    private final AdminServlet adminServlet = new AdminServlet();

    public PlacementServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor() != null ? 
                Executors.newVirtualThreadPerTaskExecutor() : Executors.newFixedThreadPool(16));

        // Mount API Servlets
        server.createContext("/api/auth", new ServletHandler(authServlet));
        server.createContext("/api/student", new ServletHandler(studentServlet));
        server.createContext("/api/recruiter", new ServletHandler(recruiterServlet));
        server.createContext("/api/admin", new ServletHandler(adminServlet));

        // Mount Static Web Handler
        server.createContext("/", new StaticFileHandler());

        server.start();

        printBanner();
    }

    public void stop() {
        if (server != null) {
            server.stop(1);
            System.out.println("[PlacementServer] Server stopped successfully.");
        }
    }

    private void printBanner() {
        System.out.println("===============================================================================");
        System.out.println("   CAMPUS PLACEMENT AND INTERNSHIP PORTAL - ENTERPRISE RUNTIME (JAVA SE 24)    ");
        System.out.println("===============================================================================");
        System.out.println("   [Adhiraj]  Module 1: Authentication & User Management   -> Mounted (/api/auth)");
        System.out.println("   [Shlok]    Module 2: Student Portal                     -> Mounted (/api/student)");
        System.out.println("   [Saurabh]  Module 3: Recruiter & Company Portal         -> Mounted (/api/recruiter)");
        System.out.println("   [Krishna]  Module 4: Admin & Analytics                  -> Mounted (/api/admin)");
        System.out.println("   [Himanshu] Module 5: Database Engine, Integration & Test -> Active (InMemory/MySQL)");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("   🚀 Portal Live at: http://localhost:" + port + "/");
        System.out.println("   🔑 Demo Credentials:");
        System.out.println("      - Student:   himanshu@gmail.com   / pass123");
        System.out.println("      - Recruiter: recruiter@google.com / pass123");
        System.out.println("      - Admin:     admin@campus.edu     / pass123");
        System.out.println("===============================================================================");
    }

    private class ServletHandler implements HttpHandler {
        private final SimpleHttpServlet servlet;

        public ServletHandler(SimpleHttpServlet servlet) {
            this.servlet = servlet;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try {
                SimpleHttpRequest req = new SimpleHttpRequest(exchange, SESSIONS);
                SimpleHttpResponse resp = new SimpleHttpResponse(exchange);

                // Track active session
                SimpleHttpSession session = req.getSession(false);
                if (session != null && !SESSIONS.containsKey(session.getId())) {
                    SESSIONS.put(session.getId(), session);
                }

                servlet.service(req, resp);
                SimpleHttpSession postSession = req.getSession(false);
                if (postSession != null && postSession.isValid()) {
                    SESSIONS.put(postSession.getId(), postSession);
                }
                resp.commit();
            } catch (Exception e) {
                e.printStackTrace();
                byte[] err = ("{\"error\": true, \"message\": \"" + e.getMessage() + "\"}").getBytes();
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(500, err.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(err);
                }
            }
        }
    }

    private static class StaticFileHandler implements HttpHandler {
        private final Path webappRoot;

        public StaticFileHandler() {
            // Check possible webapp roots
            Path current = Paths.get("src/main/webapp");
            if (Files.exists(current)) {
                this.webappRoot = current;
            } else {
                this.webappRoot = Paths.get("webapp");
            }
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) {
                path = "/index.html";
            }

            // Prevent path traversal
            Path resolved = webappRoot.resolve(path.substring(1)).normalize();
            if (!resolved.startsWith(webappRoot) || !Files.exists(resolved) || Files.isDirectory(resolved)) {
                // If not found, check if it's an html page without extension
                Path withHtml = webappRoot.resolve(path.substring(1) + ".html").normalize();
                if (Files.exists(withHtml) && !Files.isDirectory(withHtml)) {
                    resolved = withHtml;
                } else {
                    String notFound = "<h1>404 Not Found</h1><p>The requested resource " + path + " was not found.</p>";
                    byte[] bytes = notFound.getBytes();
                    exchange.getResponseHeaders().set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(404, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                    return;
                }
            }

            String mime = getMimeType(resolved.toString());
            byte[] bytes = Files.readAllBytes(resolved);
            exchange.getResponseHeaders().set("Content-Type", mime);
            exchange.getResponseHeaders().set("Cache-Control", "no-cache");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }

        private String getMimeType(String path) {
            String lower = path.toLowerCase();
            if (lower.endsWith(".html") || lower.endsWith(".htm")) return "text/html; charset=UTF-8";
            if (lower.endsWith(".css")) return "text/css; charset=UTF-8";
            if (lower.endsWith(".js")) return "application/javascript; charset=UTF-8";
            if (lower.endsWith(".json")) return "application/json; charset=UTF-8";
            if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
            if (lower.endsWith(".png")) return "image/png";
            if (lower.endsWith(".svg")) return "image/svg+xml";
            if (lower.endsWith(".ico")) return "image/x-icon";
            if (lower.endsWith(".pdf")) return "application/pdf";
            return "text/plain";
        }
    }

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }
        try {
            PlacementServer server = new PlacementServer(port);
            server.start();
        } catch (IOException e) {
            System.err.println("Failed to start Placement Server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
