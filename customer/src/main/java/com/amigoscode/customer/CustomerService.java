package com.amigoscode.customer;

import com.amigoscode.ampq.RabbitMQMessageProducer;
import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    FraudClient fraudClient,
    RabbitMQMessageProducer rabbitMQMessageProducer
) {
  public void registrationCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .build();
    //todo: check if email valid
    //todo: check if email not taken
    this.customerRepository.saveAndFlush(customer);
    //todo: check if fraudster

    FraudCheckResponse fraudCheckResponse = this.fraudClient.isFraudster(customer.getId());
    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }
    NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
        customer.getEmail(),
        String.format("Hi %s welcome to Amigoscode...", customer.getFirstName()));
    this.rabbitMQMessageProducer.publish(notificationRequest,
        "internal.exchange",
        "internal.notification.routing-key");
  }
}
