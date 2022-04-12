package com.nicoardizzoli.notification.service;

import com.nicoardizzoli.notification.model.Notification;
import com.nicoardizzoli.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(Integer customerId, String text) {
        Notification notification = Notification.builder()
                .customerId(customerId)
                .text(text)
                .build();

        notificationRepository.save(notification);
    }
}
