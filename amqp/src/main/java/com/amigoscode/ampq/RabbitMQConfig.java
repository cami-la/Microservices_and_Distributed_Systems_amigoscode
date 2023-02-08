package com.amigoscode.ampq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
  private final ConnectionFactory connectionFactory;

  @Bean
  public AmqpTemplate amqpTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory);
    MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
    rabbitTemplate.setMessageConverter(this.jacksonConverter());
    return rabbitTemplate;
  }

  @Bean
  public MessageConverter jacksonConverter() {
    return new Jackson2JsonMessageConverter();
  }
}