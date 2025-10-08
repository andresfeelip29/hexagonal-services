package com.co.technicaltest.account_service.infrastructure.mapper;


import com.co.technicaltest.account_service.domain.model.Customer;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import org.springframework.stereotype.Component;

/**
 * Class to mapper entity information and persist it in a database.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class CustomerMapper {


    public CustomerEntity toCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .name(customer.getName())
                .customerId(customer.getCustomerId())
                .username(customer.getUsername())
                .status(customer.getStatus())
                .build();
    }


    public Customer toCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .name(customerEntity.getName())
                .customerId(customerEntity.getCustomerId())
                .username(customerEntity.getUsername())
                .status(customerEntity.getStatus())
                .build();
    }

    public CustomerEntity toUpdateCustomerEntity(CustomerEntity customerEntity, Customer customer) {
        customerEntity.setName(customer.getName());
        customerEntity.setUsername(customer.getUsername());
        customerEntity.setStatus(customer.getStatus());
        return customerEntity;
    }
}
