package com.placement.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Lightweight Thread-Safe Session abstraction
 * Assigned to: Adhiraj & Himanshu (feature/authentication, feature/database-integration)
 */
public class SimpleHttpSession {
    private final String id;
    private final Map<String, Object> attributes = new ConcurrentHashMap<>();
    private final long createdAt = System.currentTimeMillis();
    private volatile long lastAccessedAt = System.currentTimeMillis();
    private volatile boolean valid = true;

    public SimpleHttpSession(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getAttribute(String name) {
        if (!valid) throw new IllegalStateException("Session is invalidated");
        lastAccessedAt = System.currentTimeMillis();
        return attributes.get(name);
    }

    public void setAttribute(String name, Object value) {
        if (!valid) throw new IllegalStateException("Session is invalidated");
        lastAccessedAt = System.currentTimeMillis();
        if (value == null) {
            attributes.remove(name);
        } else {
            attributes.put(name, value);
        }
    }

    public void removeAttribute(String name) {
        if (!valid) throw new IllegalStateException("Session is invalidated");
        attributes.remove(name);
    }

    public void invalidate() {
        valid = false;
        attributes.clear();
    }

    public boolean isValid() {
        return valid;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getLastAccessedAt() {
        return lastAccessedAt;
    }
}
