package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.customer;

import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring PostgreSQL Jpa customer persistence.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface SpringDataCustomerRespository extends JpaRepository<CustomerEntity, UUID> {
}
