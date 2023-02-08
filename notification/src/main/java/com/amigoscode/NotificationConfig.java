package com.amigoscode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public record NotificationConfig(
    @Value("${rabbitmq.exchanges.internal}")
    String internalExchange,
    @Value("${rabbitmq.queues.notification}")
    String notificationQueue,
    @Value("${rabbitmq.routing-keys.internal-notification}")
    String internalNotificationRoutingKey
) {
}
