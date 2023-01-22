package com.amigoscode.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {
  @GetMapping(path = "api/fraud/{customerId}")
  FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
