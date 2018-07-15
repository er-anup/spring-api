package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import java.util.List;
@Service
public interface NotificationService {
    List<Notification> getNewNotifications(int id);
    List<Notification> getAllNotifications(int id);
    void addnotification(int user_id, String message) ;
}


