package com.co.technicaltest.application.usecase;

import com.co.technicaltest.application.dto.CustomerLoginResponseDTO;
import com.co.technicaltest.application.dto.CustomerResponseDTO;
import com.co.technicaltest.application.exception.CustomerNotFoundException;
import com.co.technicaltest.application.mapper.CustomerDTOMapper;
import com.co.technicaltest.domain.model.Customer;
import com.co.technicaltest.domain.port.input.CustomerUseCase;
import com.co.technicaltest.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.infrastructure.shared.enums.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Use case port implemetation for Customer.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerRepositoryPort repository;

    private final CustomerDTOMapper customerDTOMapper;

    public CustomerService(CustomerRepositoryPort repository, CustomerDTOMapper customerDTOMapper) {
        this.repository = repository;
        this.customerDTOMapper = customerDTOMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponseDTO> getAllCustomer() {

        log.info("Metodo: {}, para obtener todos los clientes",
                "[getAllCustomer]");

        return this.repository.findAll().stream()
                .map(this.customerDTOMapper::toCustomerDTO)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<CustomerLoginResponseDTO> findCustomerByUsername(String username) {

        log.info("Metodo: {}, para obtener cliente por nombre de usuario: {}",
                "[findCustomerByUsername]", username);

        return this.repository.findByCustomerUsername(username)
                .map(this.customerDTOMapper::customerLoginDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<CustomerResponseDTO> findUserByCustomerId(UUID customerId) {

        log.info("Metodo: {}, para obtener cliente por su id UUID: {}",
                "[findUserByCustomerId]", customerId.toString());

        return Optional.ofNullable(this.repository.findByCustomerId(customerId)
                .map(this.customerDTOMapper::toCustomerDTO)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), customerId.toString()))));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Optional<CustomerResponseDTO> saveCustomer(Customer customer) {

        if (Objects.isNull(customer)) return Optional.empty();

        customer.updateCustomerId();

        log.info("Metodo: {}, para guardar cliente con id generado: {}",
                "[saveCustomer]", customer.getCustomerId().toString());

        return this.repository.saveCustomer(customer)
                .map(this.customerDTOMapper::toCustomerDTO);

    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Optional<CustomerResponseDTO> updateCustomer(Customer customer, UUID id) {

        log.info("Metodo: {}, para actualizar cliente con id generado: {}",
                "[updateCustomer]", id.toString());

        if (Objects.isNull(customer)) return Optional.empty();

        return this.repository.updateCustomer(customer, id)
                .map(this.customerDTOMapper::toCustomerDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteCustomer(UUID customerId) {

        log.info("Metodo: {}, para eliminar cliente por id: {}",
                "[deleteCustomer]", customerId.toString());

        this.repository.deleteCustomer(customerId);

    }

}
