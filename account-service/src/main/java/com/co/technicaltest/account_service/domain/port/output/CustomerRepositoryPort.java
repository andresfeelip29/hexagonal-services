package com.co.technicaltest.account_service.domain.port.output;

import com.co.technicaltest.account_service.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;


/**
 * Repositorio port for customer.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface CustomerRepositoryPort {


    /**
     * Method for find customer by id
     *
     * @param customerId the customer id.
     * @return Optional<Account> account data domain
     */
    Optional<Customer> findCustomerByCustomerId(UUID customerId);

    /**
     * Method for save customer
     *
     * @param customer data customer information for save.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> saveCustomer(Customer customer);

    /**
     * Method for update customer
     *
     * @param customer data customer information for save.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> updateCustomer(Customer customer);


    /**
     * Method for delete customer
     *
     * @param customerId UUID id for intentificacion customer.
     */
    void deleteCustomer(UUID customerId);
}
