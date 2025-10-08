package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.customer;

import com.co.technicaltest.account_service.domain.exception.CustomerNotFoundException;
import com.co.technicaltest.account_service.domain.model.Customer;
import com.co.technicaltest.account_service.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import com.co.technicaltest.account_service.infrastructure.mapper.CustomerMapper;
import com.co.technicaltest.account_service.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository adapter implemetation for customer.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {


    private final SpringDataCustomerRespository repository;

    private final CustomerMapper customerMapper;


    public JpaCustomerRepositoryAdapter(SpringDataCustomerRespository repository,
                                        CustomerMapper customerMapper) {
        this.repository = repository;
        this.customerMapper = customerMapper;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> findCustomerByCustomerId(UUID customerId) {

        CustomerEntity customer = this.repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(ExceptionMessage.CUSTOMER_NOT_FOUND.getMessage(), customerId.toString())));

        if (Objects.isNull(customer)) return Optional.empty();

        return Optional.ofNullable(this.customerMapper.toCustomer(customer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> saveCustomer(Customer customer) {

        CustomerEntity customerEntity =
                this.customerMapper.toCustomerEntity(customer);

        this.repository.save(customerEntity);

        return Optional.ofNullable(this.customerMapper.toCustomer(customerEntity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> updateCustomer(Customer customer) {

        CustomerEntity customerEntity = this.repository.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(ExceptionMessage.CUSTOMER_NOT_FOUND.getMessage(), customer.getCustomerId().toString())));

        CustomerEntity customerUpdated =
                this.customerMapper.toUpdateCustomerEntity(customerEntity, customer);

        this.repository.save(customerUpdated);

        return Optional.ofNullable(this.customerMapper.toCustomer(customerUpdated));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomer(UUID customerId) {

        CustomerEntity customer = this.repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format(ExceptionMessage.CUSTOMER_NOT_FOUND.getMessage(), customerId.toString())));
        this.repository.delete(customer);

    }
}
