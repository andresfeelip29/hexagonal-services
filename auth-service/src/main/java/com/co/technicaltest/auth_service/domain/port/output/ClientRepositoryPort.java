package com.co.technicaltest.auth_service.domain.port.output;

import com.co.technicaltest.auth_service.domain.model.Customer;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;

import java.util.Optional;

/**
 * Repositorio port.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface ClientRepositoryPort {

    /**
     * Method to obtain the client by username on customer service.
     *
     * @param username customer username.
     * @return Optional<RegisterRequestDTO> Optional of data customer registered
     */
    Optional<RegisterRequestDTO> findByUsernameOnCustomerService(String username);

    /**
     * Method for registering customer information.
     *
     * @param customer Domain model for register customer.
     * @return Optional<RegisterResponseDTO> Optional of data customer registered
     */
    Optional<RegisterResponseDTO> saveOnCustomerService(Customer customer);
}
