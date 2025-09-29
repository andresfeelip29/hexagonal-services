package com.co.technicaltest.infrastructure.adapter.output.persistence.account;

import com.co.technicaltest.infrastructure.adapter.output.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring MySQL Jpa Account persistence.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {
}
