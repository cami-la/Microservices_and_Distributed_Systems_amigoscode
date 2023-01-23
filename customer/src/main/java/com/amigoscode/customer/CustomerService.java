package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationClient;
import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    FraudClient fraudClient,
    NotificationClient notificationClient
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
    if(fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }
    //todo: make it sync. i.e add to queue
    this.notificationClient.sendNotification(
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, welcome to Amigoscode...", customer.getFirstName())
        )
    );
  }
}
