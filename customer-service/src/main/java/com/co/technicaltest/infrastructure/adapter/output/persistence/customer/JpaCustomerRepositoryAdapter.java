package com.co.technicaltest.infrastructure.adapter.output.persistence.customer;

import com.co.technicaltest.application.exception.CustomerNotFoundException;
import com.co.technicaltest.domain.model.Customer;
import com.co.technicaltest.domain.port.output.CustomerRepositoryPort;
import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import com.co.technicaltest.infrastructure.mapper.CostumerMapper;
import com.co.technicaltest.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository adapter implemetation for Customer.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerRespository repository;

    private final CostumerMapper costumerMapper;

    public JpaCustomerRepositoryAdapter(SpringDataCustomerRespository repository,
                                        CostumerMapper costumerMapper) {
        this.repository = repository;
        this.costumerMapper = costumerMapper;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> findByCustomerUsername(String username) {

        CustomerEntity customerEntity =
                this.repository.findCustomerEntitiesByUsername(username)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), username)));

        return Optional.of(this.costumerMapper.customerEntityToCustomer(customerEntity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> findByCustomerId(UUID id) {

        CustomerEntity customerEntity =
                this.repository.findCustomerEntitiesByCustomerId(id)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), id)));

        if (Objects.isNull(customerEntity)) return Optional.empty();

        return Optional.of(this.costumerMapper.customerEntityToCustomer(customerEntity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> findAll() {
        return this.repository.findAll().stream()
                .map(this.costumerMapper::customerEntityToCustomer)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> saveCustomer(Customer customer) {

        CustomerEntity customerEntity =
                this.costumerMapper.toCustomerEntity(customer);

        this.repository.save(customerEntity);
        return Optional.of(this.costumerMapper.customerEntityToCustomer(customerEntity));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Customer> updateCustomer(Customer customer, UUID id) {

        CustomerEntity customerEntity =
                this.repository.findCustomerEntitiesByCustomerId(id)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), id)));

        CustomerEntity customerEntityUpdated =
                this.costumerMapper.updateCustomerEntity(customerEntity, customer);

        this.repository.save(customerEntityUpdated);
        return Optional.of(this.costumerMapper.customerEntityToCustomer(customerEntityUpdated));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomer(UUID customerId) {

        CustomerEntity customerEntity =
                this.repository.findCustomerEntitiesByCustomerId(customerId)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), customerId)));

        this.repository.delete(customerEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccountFromCustomer(UUID customerId, Long accountId) {

        CustomerEntity customerEntity =
                this.repository.findCustomerEntitiesByCustomerId(customerId)
                        .orElseThrow(() -> new CustomerNotFoundException(
                                String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), customerId)));

        if (!Objects.isNull(customerEntity.getAccounts())) {
            boolean removed = customerEntity.getAccounts()
                    .removeIf(acc -> acc.getId()
                            .equals(accountId));

            if (removed) this.repository.save(customerEntity);

        }

    }
}
