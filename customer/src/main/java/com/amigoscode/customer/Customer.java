package com.amigoscode.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
