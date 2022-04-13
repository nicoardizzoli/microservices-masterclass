package com.nicoardizzoli.notification.rabbitmq;

import com.nicoardizzoli.clients.notification.NotificationDto;
import com.nicoardizzoli.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "notification.queue")
    public void consumer(NotificationDto notificationDto){
        log.info("Consumed {} from queue", notificationDto);
        notificationService.sendNotification(notificationDto);

    }
}
