package com.co.technicaltest.auth_service.infrastructure.adapter.output.persistence;

import com.co.technicaltest.auth_service.domain.model.Customer;
import com.co.technicaltest.auth_service.domain.port.output.ClientRepositoryPort;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;
import com.co.technicaltest.auth_service.infrastructure.adapter.output.client.ClientFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adpater for Feing client for HTTP .
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Component
public class ClientFeignAdapter implements ClientRepositoryPort {

    private final ClientFeign clientFeign;

    public ClientFeignAdapter(ClientFeign clientFeign) {
        this.clientFeign = clientFeign;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegisterRequestDTO> findByUsernameOnCustomerService(String username) {
        return Optional.ofNullable(this.clientFeign.findByUsernameOnCustomerService(username));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegisterResponseDTO> saveOnCustomerService(Customer customer) {
        return Optional.ofNullable(this.clientFeign.saveOnCustomerService(customer));
    }
}
