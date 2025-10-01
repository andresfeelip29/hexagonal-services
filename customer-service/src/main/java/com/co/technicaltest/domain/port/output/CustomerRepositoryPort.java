package com.co.technicaltest.domain.port.output;


import com.co.technicaltest.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositorio interface for customer port.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface CustomerRepositoryPort {

    /**
     * Method for find customer by identificacion
     *
     * @param id UUUID for intentificacion customer.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> findByCustomerId(UUID id);

    /**
     * Method for find customer by username
     *
     * @param username customer username.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> findByCustomerUsername(String username);

    /**
     * Method for find all customer.
     *
     * @return List<Customer> customer list data domain
     */
    List<Customer> findAll();

    /**
     * Method for save customer
     *
     * @param customer data information.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> saveCustomer(Customer customer);

    /**
     * Method for update customer
     *
     * @param customer data information.
     * @param id UUUID for intentificacion customer.
     * @return Optional<Customer> customer data domain
     */
    Optional<Customer> updateCustomer(Customer customer, UUID id);

    /**
     * Method for delete customer
     *
     * @param customerId ID customer.
     */
    void deleteCustomer( UUID customerId);


    /**
     * Method for delete account form customer
     *
     * @param customerId ID customer.
     */
    void deleteAccountFromCustomer(UUID customerId, Long accountId);

}
