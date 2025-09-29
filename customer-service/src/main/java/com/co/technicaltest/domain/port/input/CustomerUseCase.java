package com.co.technicaltest.domain.port.input;


import com.co.technicaltest.application.dto.CustomerLoginResponseDTO;
import com.co.technicaltest.application.dto.CustomerResponseDTO;
import com.co.technicaltest.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Customer use case.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface CustomerUseCase {

    /**
     * Method for get all customer .
     *
     *
     * @return List<CustomerResponseDTO>  List all customer on response
     */
    List<CustomerResponseDTO> getAllCustomer();

    /**
     * Method for obtaining a customer by username.
     *
     * @param username username.
     * @return Optional<CustomerLoginResponseDTO> Optional response data login
     */
    Optional<CustomerLoginResponseDTO> findCustomerByUsername(String username);

    /**
     * Method for obtaining a customer by customer id.
     *
     * @param customerId customer id.
     * @return Optional<CustomerResponseDTO> Optional response data customer
     */
    Optional<CustomerResponseDTO> findUserByCustomerId(UUID customerId);

    /**
     * Method for delete a customer by customer id.
     *
     * @param customerId customer id.
     *
     */
    void deleteCustomer(UUID customerId);

    /**
     * Method for save a customer by customer domain data.
     *
     * @param customer customer domain data.
     * @return Optional<CustomerResponseDTO> Optional response data customer
     */
    Optional<CustomerResponseDTO> saveCustomer(Customer customer);

    /**
     * Method for dupate a customer by customer domain data.
     *
     * @param customer customer domain data.
     * @param id customer id.
     * @return Optional<CustomerResponseDTO> Optional response data customer
     */
    Optional<CustomerResponseDTO> updateCustomer(Customer customer, UUID id);

}
