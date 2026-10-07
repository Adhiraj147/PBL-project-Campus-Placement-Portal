package com.placement.server;

import java.io.IOException;

/**
 * Base Abstract Servlet Class
 * Assigned to: Himanshu (feature/database-integration)
 */
public abstract class SimpleHttpServlet {

    public void doGet(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        resp.sendError(405, "HTTP GET method not supported by this endpoint");
    }

    public void doPost(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        resp.sendError(405, "HTTP POST method not supported by this endpoint");
    }

    public void service(SimpleHttpRequest req, SimpleHttpResponse resp) throws IOException {
        String method = req.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            doGet(req, resp);
        } else if ("POST".equalsIgnoreCase(method)) {
            doPost(req, resp);
        } else {
            resp.sendError(405, "Method " + method + " not supported");
        }
    }
}
