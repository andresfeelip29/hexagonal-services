package com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.account;


import com.co.technicaltest.account_service.infrastructure.adapter.output.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring PostgreSQL Jpa account persistence.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface SpringDataAccountRespository extends JpaRepository<AccountEntity,Long> {


    /**
     * Method for find account entity by accunt number
     *
     * @param accountNumber account number identificacion.
     * @return AccountEntity entity account
     */
    Optional<AccountEntity> findAccountEntitiesByAccountNumber(String accountNumber);
}
