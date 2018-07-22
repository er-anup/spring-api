package org.upgrad.services;

import org.springframework.stereotype.Service;
import java.util.List;
import org.upgrad.models.Notification;

@Service
public interface NotificationService {

    List<Notification> getNewNotifications(int id);
    List<Notification> getAllNotifications(int id);
    void addNotification(int userId, String message);
    void deleteNotification(int userId);
}
