package com.amigoscode.notification;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class NotificationConfig {
  @Value("${rabbitmq.exchanges.internal}")
  private String internalExchange;
  @Value("${rabbitmq.queues.notification}")
  private String notificationQueue;
  @Value("${rabbitmq.routing-keys.internal-notification}")
  private String internalNotificationRoutingKey;

  @Bean
  public TopicExchange internalTopicExchange() {
    return new TopicExchange(this.internalExchange);
  }

  @Bean
  public Queue notificationQueues() {
    return new Queue(this.notificationQueue);
  }

  @Bean
  public Binding internalToNotificationBinding() {
    return BindingBuilder
        .bind(this.notificationQueues())
        .to(this.internalTopicExchange())
        .with(this.getInternalNotificationRoutingKey());
  }
}