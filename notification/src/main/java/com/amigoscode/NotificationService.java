package com.amigoscode;

import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(
  NotificationRepository notificationRepository
) {
  public void send(NotificationRequest notificationRequest) {
    this.notificationRepository.save(
        Notification.builder()
            .toCustomerId(notificationRequest.toCustomerId())
            .toCustomerEmail(notificationRequest.toCustomerName())
            .sender("Amigoscode")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build()
    );
  }
}
