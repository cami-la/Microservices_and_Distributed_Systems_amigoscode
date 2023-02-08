package com.amigoscode.ampq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record RabbitMQMessageProducer(
    AmqpTemplate amqpTemplate
) {

  public void publish(Object payload, String exchange, String routingKey) {
    log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    this.amqpTemplate.convertAndSend(exchange, routingKey, payload);
    log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
  }
}
