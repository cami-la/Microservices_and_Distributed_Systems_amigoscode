package com.amigoscode.fraud;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
public class FraudCheckHistory {
  @Id
  @SequenceGenerator(
      name = "fraud_id_sequence",
      sequenceName = "fraud_id_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "fraud_id_sequence"
  )
  private Integer id;
  private Integer customerId;
  private Boolean isFraudster;
  private LocalDateTime createAt;
}
