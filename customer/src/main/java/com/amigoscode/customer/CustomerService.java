package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
    CustomerRepository customerRepository,
    RestTemplate restTemplate,
    FraudClient fraudClient
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
    //todo: send notification
  }
}
