package com.co.technicaltest.domain.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Object domain for customer.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Data
@Builder
public class Customer {
    private UUID customerId;
    private String username;
    private String password;
    private Boolean status;
    private String name;
    private Integer age;
    private String gender;
    private String identification;
    private String address;
    private String phone;
    private List<Account> accounts;

    public void updateCustomerId() {
        if (Objects.isNull(this.customerId)) {
            this.customerId = UUID.randomUUID();
        }
    }

}
