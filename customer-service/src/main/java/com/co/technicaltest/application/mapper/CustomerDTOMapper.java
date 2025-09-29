package com.co.technicaltest.application.mapper;

import com.co.technicaltest.application.dto.AccountDetailDTO;
import com.co.technicaltest.application.dto.CustomerLoginResponseDTO;
import com.co.technicaltest.application.dto.CustomerRequestDTO;
import com.co.technicaltest.application.dto.CustomerResponseDTO;
import com.co.technicaltest.domain.model.Account;
import com.co.technicaltest.domain.model.Customer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class to mapper DTO from domain objetc.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class CustomerDTOMapper {

    private final PasswordEncoder passwordEncoder;

    public CustomerDTOMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerResponseDTO toCustomerDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .customerId(customer.getCustomerId())
                .username(customer.getUsername())
                .status(customer.getStatus())
                .phone(customer.getPhone())
                .age(customer.getAge())
                .name(customer.getName())
                .address(customer.getAddress())
                .identification(customer.getIdentification())
                .gender(customer.getGender())
                .accountDetail(this.toAccountDetailListDTO(customer.getAccounts()))
                .build();
    }

    public CustomerLoginResponseDTO customerLoginDTO(Customer customer) {
        return CustomerLoginResponseDTO.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .status(customer.getStatus())
                .build();
    }

    public Customer customerRequestDTOCustomer(CustomerRequestDTO customerRequestDTO) {
        return Customer.builder()
                .username(customerRequestDTO.username())
                .password(passwordEncoded(customerRequestDTO.password()))
                .status(customerRequestDTO.status())
                .name(customerRequestDTO.name())
                .address(customerRequestDTO.address())
                .age(customerRequestDTO.age())
                .gender(customerRequestDTO.gender())
                .identification(customerRequestDTO.identification())
                .phone(customerRequestDTO.phone())
                .build();
    }


    private List<AccountDetailDTO> toAccountDetailListDTO(List<Account> account) {
        if (Objects.isNull(account)) return Collections.emptyList();
        return account.stream()
                .map(this::toAccountDetailDTO)
                .toList();

    }


    private AccountDetailDTO toAccountDetailDTO(Account account) {
        return AccountDetailDTO.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }

    private String passwordEncoded(String password) {
        if (!(password != null && password.startsWith("$2a$")))
            return passwordEncoder.encode(password);
        return password;
    }
}
