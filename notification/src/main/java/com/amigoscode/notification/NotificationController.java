package com.amigoscode.notification;

import lombok.extern.slf4j.Slf4j;
import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/notification")
@RestController
@Slf4j
public record NotificationController(
    NotificationService notificationService
) {
  @PostMapping
  public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
    log.info("New Notification... {}", notificationRequest);
    this.notificationService.send(notificationRequest);
  }
}
