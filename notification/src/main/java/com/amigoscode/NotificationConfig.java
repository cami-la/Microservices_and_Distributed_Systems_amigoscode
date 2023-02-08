package com.amigoscode;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
        .to(internalTopicExchange())
        .with(this.internalNotificationRoutingKey);
  }
}
