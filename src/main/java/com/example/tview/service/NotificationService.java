package com.example.tview.service;

import com.example.tview.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    public NotificationRepository notificationRepository;
}
