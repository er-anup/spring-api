package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.repositories.NotificationRepository;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNewNotifications(int id) {
        List<Notification> newNotifications = notificationRepository.findNotification(id);
        notificationRepository.updateRead(id);
        return newNotifications;
    }
    @Override
    public List<Notification> getAllNotifications(int id) {
        List<Notification> allNotifications = notificationRepository.findAllNotification(id);
        notificationRepository.updateAllRead();
        return allNotifications;
    }

    @Override
    public void addnotification( int user_id , String message) {
        notificationRepository.addNotification(user_id, message) ;
    }

}

