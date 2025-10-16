package com.co.technicaltest.transaction_service.domain.port.output;

import com.co.technicaltest.transaction_service.domain.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repositorio port for transaction.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface TransactionRepositoryPort {

    /**
     * Method for find all transactions.
     *
     * @return Flux<Transaction> transactions list data domain
     */
    Flux<Transaction> findAllTransactions();

    /**
     * Method for find transaction by id
     *
     * @param id the transaction id.
     * @return Mono<Transaction> transaction data domain
     */
    Mono<Transaction> findTransactionById(UUID id);

    /**
     * Method for save transaction
     *
     * @param transaction data transaction information for save.
     * @return Mono<Transaction> customer data domain
     */
    Mono<Transaction> saveTransaction(Transaction transaction);


    /**
     * Method for update transaction
     *
     * @param transaction data transaction information for update.
     * @return Mono<Transaction> customer data domain
     */
    Mono<Transaction> updateTransaction(Transaction transaction, UUID id);


    /**
     * Method for find all transactions by date range.
     *
     * @param dateFrom date range from init.
     * @param dateTo   date range to end.
     * @return Flux<Transaction> transactions list data domain
     */
    Flux<Transaction> findAllTransactionsByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);
}
