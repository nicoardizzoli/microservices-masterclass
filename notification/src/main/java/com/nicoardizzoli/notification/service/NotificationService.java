package com.nicoardizzoli.notification.service;

import com.nicoardizzoli.clients.notification.NotificationDto;
import com.nicoardizzoli.notification.model.Notification;
import com.nicoardizzoli.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .customerId(notificationDto.customerId())
                .text(notificationDto.text())
                .build();

        notificationRepository.save(notification);
    }
}
