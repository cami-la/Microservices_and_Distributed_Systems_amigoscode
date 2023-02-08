package com.amigoscode.notification;

import com.amigoscode.ampq.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication(
    scanBasePackages = {"com.amigoscode.notification", "com.amigoscode.ampq"}
)
public class NotificationApplication {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }
  /*@Bean
  CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig notificationConfig) {
    return args -> producer.publish(new Person("Cami", 29),
        notificationConfig.internalTopicExchange().getName(),
        notificationConfig.getInternalNotificationRoutingKey());
  }

  record Person(String name, int age){}*/
}
