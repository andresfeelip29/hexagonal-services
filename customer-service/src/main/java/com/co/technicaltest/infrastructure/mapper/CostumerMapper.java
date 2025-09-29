package com.co.technicaltest.infrastructure.mapper;


import com.co.technicaltest.domain.model.Customer;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Class to mapper entity information and persist it in a database.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */

@Component
public class CostumerMapper {


    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder;

    public CostumerMapper(AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerEntity toCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .customerId(customer.getCustomerId())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .status(customer.getStatus())
                .name(customer.getName())
                .age(customer.getAge())
                .address(customer.getAddress())
                .gender(customer.getGender())
                .identification(customer.getIdentification())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .build();
    }

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerId(customerEntity.getCustomerId())
                .username(customerEntity.getUsername())
                .password(customerEntity.getPassword())
                .status(customerEntity.getStatus())
                .name(customerEntity.getName())
                .age(customerEntity.getAge())
                .gender(customerEntity.getGender())
                .identification(customerEntity.getIdentification())
                .address(customerEntity.getAddress())
                .phone(customerEntity.getPhone())
                .accounts(this.accountMapper.accountEntityToAccountList(customerEntity.getAccounts()))
                .build();
    }

    public CustomerEntity updateCustomerEntity(CustomerEntity customerEntity,
                                               Customer customer) {
        customerEntity.setUsername(customer.getUsername());
        customerEntity.setPassword(passwordEncoded(customer.getPassword()));
        customerEntity.setStatus(customer.getStatus());
        customerEntity.setName(customer.getName());
        customerEntity.setAge(customer.getAge());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setGender(customer.getGender());
        customerEntity.setIdentification(customer.getIdentification());
        customerEntity.setPhone(customer.getPhone());
        return customerEntity;
    }

    private String passwordEncoded(String password) {
        if (!(password != null && password.startsWith("$2a$")))
            return passwordEncoder.encode(password);
        return password;
    }

}
