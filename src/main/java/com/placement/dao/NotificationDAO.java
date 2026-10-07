package com.placement.dao;

import com.placement.db.DBConnection;
import com.placement.db.InMemoryDB;
import com.placement.model.Notification;

import java.util.List;

/**
 * Notification DAO
 * Assigned to: Himanshu (feature/database-integration)
 */
public class NotificationDAO {
    private final InMemoryDB inMemory = DBConnection.getInMemoryStore();

    public List<Notification> findByUserId(int userId) {
        return inMemory.getNotificationsByUser(userId);
    }

    public Notification send(Notification notification) {
        return inMemory.saveNotification(notification);
    }

    public void markAllRead(int userId) {
        inMemory.markAllNotificationsRead(userId);
    }
}
