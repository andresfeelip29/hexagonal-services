package com.co.technicaltest.infrastructure.adapter.output.persistence.customer;

import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;


/**
 * Spring MySQL Jpa Customer persistence.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface SpringDataCustomerRespository extends JpaRepository<CustomerEntity, Long> {

    /**
     * Method for find customer entity from id
     *
     * @param customerId customer id.
     * @return CustomerEntity entity customer
     */
    Optional<CustomerEntity> findCustomerEntitiesByCustomerId(UUID customerId);

    /**
     * Method for find customer entity from username
     *
     * @param username customer username.
     * @return CustomerEntity entity customer
     */
    CustomerEntity findCustomerEntitiesByUsername(String username);
}
