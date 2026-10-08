package com.placement.dao;

import com.placement.model.PortalNotification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PortalNotificationDAO {

    private Connection connection;

    public PortalNotificationDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PortalNotification> getNotificationsByStudentId(
            int studentId) {

        List<PortalNotification> notifications =
                new ArrayList<>();

        String sql =
                "SELECT notification_id, student_id, title, " +
                "message, notification_date, is_read " +
                "FROM portal_notifications " +
                "WHERE student_id = ? " +
                "ORDER BY notification_date DESC";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    PortalNotification notification =
                            new PortalNotification();

                    notification.setNotificationId(
                            resultSet.getInt("notification_id"));

                    notification.setStudentId(
                            resultSet.getInt("student_id"));

                    notification.setTitle(
                            resultSet.getString("title"));

                    notification.setMessage(
                            resultSet.getString("message"));

                    notification.setNotificationDate(
                            String.valueOf(
                                    resultSet.getTimestamp(
                                            "notification_date")));

                    notification.setRead(
                            resultSet.getBoolean("is_read"));

                    notifications.add(notification);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
