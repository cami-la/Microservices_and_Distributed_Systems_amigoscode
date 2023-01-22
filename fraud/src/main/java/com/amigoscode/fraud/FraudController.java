package com.amigoscode.fraud;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/fraud-check")
@RestController
public record FraudController(
    FraudCheckService fraudCheckService
) {

  @GetMapping(path="{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    boolean isFraudulentCustomer = this.fraudCheckService.isFraudulentCustomer(customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
