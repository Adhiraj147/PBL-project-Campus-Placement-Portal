package com.placement.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Lightweight HTTP Request Adapter wrapping HttpExchange
 * Mimics standard HttpServletRequest methods
 * Assigned to: Himanshu & Adhiraj (feature/database-integration, feature/authentication)
 */
public class SimpleHttpRequest {
    private final HttpExchange exchange;
    private final Map<String, SimpleHttpSession> sessionStore;
    private final Map<String, String> parameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();
    private final Map<String, String> cookies = new HashMap<>();
    private String body;
    private SimpleHttpSession session;

    public SimpleHttpRequest(HttpExchange exchange, Map<String, SimpleHttpSession> sessionStore) throws IOException {
        this.exchange = exchange;
        this.sessionStore = sessionStore;
        parseHeadersAndCookies();
        parseQueryParams();
        parseBodyParams();
        resolveSession();
    }

    private void parseHeadersAndCookies() {
        for (Map.Entry<String, List<String>> entry : exchange.getRequestHeaders().entrySet()) {
            if (!entry.getValue().isEmpty()) {
                headers.put(entry.getKey().toLowerCase(), entry.getValue().get(0));
            }
            if ("cookie".equalsIgnoreCase(entry.getKey())) {
                for (String val : entry.getValue()) {
                    String[] parts = val.split(";");
                    for (String part : parts) {
                        String[] pair = part.trim().split("=", 2);
                        if (pair.length == 2) {
                            cookies.put(pair[0].trim(), pair[1].trim());
                        }
                    }
                }
            }
        }
    }

    private void parseQueryParams() {
        String query = exchange.getRequestURI().getRawQuery();
        if (query != null && !query.isEmpty()) {
            parseFormEncoded(query);
        }
    }

    private void parseBodyParams() throws IOException {
        String method = getMethod();
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
            InputStream is = exchange.getRequestBody();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int read;
            while ((read = is.read(buf)) != -1) {
                baos.write(buf, 0, read);
            }
            this.body = baos.toString(StandardCharsets.UTF_8);

            String contentType = headers.get("content-type");
            if (contentType != null && contentType.contains("application/x-www-form-urlencoded")) {
                parseFormEncoded(this.body);
            }
        }
    }

    private void parseFormEncoded(String data) {
        String[] pairs = data.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length > 0) {
                String key = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
                String val = kv.length > 1 ? URLDecoder.decode(kv[1], StandardCharsets.UTF_8) : "";
                parameters.put(key, val);
            }
        }
    }

    private void resolveSession() {
        String sessionId = cookies.get("JSESSIONID");
        if (sessionId != null && sessionStore != null && sessionStore.containsKey(sessionId)) {
            SimpleHttpSession existing = sessionStore.get(sessionId);
            if (existing != null && existing.isValid()) {
                this.session = existing;
            }
        }
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public String getPath() {
        return exchange.getRequestURI().getPath();
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public String getHeader(String name) {
        return headers.get(name.toLowerCase());
    }

    public String getCookie(String name) {
        return cookies.get(name);
    }

    public String getBody() {
        return body != null ? body : "";
    }

    public SimpleHttpSession getSession() {
        return getSession(true);
    }

    public SimpleHttpSession getSession(boolean create) {
        if (session != null && session.isValid()) {
            return session;
        }
        if (create) {
            String newId = UUID.randomUUID().toString().replace("-", "");
            session = new SimpleHttpSession(newId);
            if (sessionStore != null) {
                sessionStore.put(newId, session);
            }
        }
        return session;
    }

    public HttpExchange getExchange() {
        return exchange;
    }
}
