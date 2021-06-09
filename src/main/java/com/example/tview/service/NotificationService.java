package com.example.tview.service;

import com.example.tview.model.Notification;
import com.example.tview.model.User;
import com.example.tview.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationService {
    @Autowired
    public NotificationRepository notificationRepository;

    public Notification createNotification(User user, String content){
        Notification notification = new Notification();
        notification.setDate(new Date());
        notification.setContent(content);
        notification.setUser(user);
        notificationRepository.save(notification);
        return notification;
    }
}
