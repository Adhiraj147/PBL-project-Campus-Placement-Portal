package com.placement.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * Lightweight HTTP Response Adapter wrapping HttpExchange
 * Mimics standard HttpServletResponse methods
 * Assigned to: Himanshu & Adhiraj (feature/database-integration, feature/authentication)
 */
public class SimpleHttpResponse {
    private final HttpExchange exchange;
    private int status = 200;
    private String contentType = "text/html; charset=UTF-8";
    private final StringWriter stringWriter = new StringWriter();
    private final PrintWriter writer = new PrintWriter(stringWriter);
    private boolean committed = false;

    public SimpleHttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setHeader(String name, String value) {
        exchange.getResponseHeaders().set(name, value);
    }

    public void addHeader(String name, String value) {
        exchange.getResponseHeaders().add(name, value);
    }

    public void addCookie(String name, String value, String path, int maxAge) {
        String cookieStr = String.format("%s=%s; Path=%s; HttpOnly; SameSite=Lax", name, value, path != null ? path : "/");
        if (maxAge >= 0) {
            cookieStr += "; Max-Age=" + maxAge;
        }
        addHeader("Set-Cookie", cookieStr);
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void sendRedirect(String location) throws IOException {
        setStatus(302);
        setHeader("Location", location);
        commit();
    }

    public void sendJson(String json) throws IOException {
        setContentType("application/json; charset=UTF-8");
        getWriter().write(json);
        commit();
    }

    public void sendError(int sc, String msg) throws IOException {
        setStatus(sc);
        setContentType("application/json; charset=UTF-8");
        getWriter().write("{\"error\": true, \"status\": " + sc + ", \"message\": \"" + (msg != null ? msg.replace("\"", "\\\"") : "") + "\"}");
        commit();
    }

    public void commit() throws IOException {
        if (committed) return;
        committed = true;
        writer.flush();
        byte[] bytes = stringWriter.toString().getBytes(StandardCharsets.UTF_8);
        setHeader("Content-Type", contentType);
        setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
