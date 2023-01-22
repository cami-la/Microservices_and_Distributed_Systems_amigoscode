package com.amigoscode.fraud;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/fraud-check")
@RestController
@Slf4j
public record FraudController(
    FraudCheckService fraudCheckService
) {

  @GetMapping(path="{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    boolean isFraudulentCustomer = this.fraudCheckService.isFraudulentCustomer(customerId);
    log.info("fraud check request for customer {}", customerId);
    return new FraudCheckResponse(isFraudulentCustomer);
  }
}
