package com.co.technicaltest.auth_service.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {
    private String username;
    private String password;
    private Boolean status;
    private String name;
    private Integer age;
    private String gender;
    private String identification;
    private String address;
    private String phone;
}
