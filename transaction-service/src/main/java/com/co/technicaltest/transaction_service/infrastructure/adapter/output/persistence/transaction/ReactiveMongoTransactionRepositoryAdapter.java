package com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.transaction;

import com.co.technicaltest.transaction_service.domain.exception.TransactionException;
import com.co.technicaltest.transaction_service.domain.model.Transaction;
import com.co.technicaltest.transaction_service.domain.port.output.TransactionRepositoryPort;
import com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.collections.TransactionCollection;
import com.co.technicaltest.transaction_service.infrastructure.mapper.TransactionMapper;
import com.co.technicaltest.transaction_service.infrastructure.shared.enums.ExceptionMessage;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repository adapter implemetation for transaction.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Repository
public class ReactiveMongoTransactionRepositoryAdapter implements TransactionRepositoryPort {


    private final SpringDataTransactionRespository respository;

    private final TransactionMapper mapper;

    public ReactiveMongoTransactionRepositoryAdapter(SpringDataTransactionRespository respository,
                                                     TransactionMapper mapper) {
        this.respository = respository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<Transaction> findAllTransactions() {

        return this.respository.findAll()
                .map(this.mapper::toTransaction);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Transaction> findTransactionById(UUID id) {

        return this.respository.findById(id)
                .map(this.mapper::toTransaction)
                .switchIfEmpty(Mono.error(() ->
                        new TransactionException(
                                String.format(ExceptionMessage.TRANSACTION_NOT_FOUND.getMessage(), id))));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Transaction> saveTransaction(Transaction transaction) {

        TransactionCollection transactionCollection =
                this.mapper.toTransactionCollection(transaction);

        return this.respository.save(transactionCollection)
                .map(this.mapper::toTransaction);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Transaction> updateTransaction(Transaction transaction, UUID id) {

        return this.respository.findById(id)
                .switchIfEmpty(Mono.error(() ->
                        new TransactionException(
                                String.format(ExceptionMessage.TRANSACTION_NOT_FOUND.getMessage(), id))))
                .flatMap(collection -> {
                    TransactionCollection transactionCollectionUpdate = this.mapper.toUpdateTransactionCollection(
                            collection, transaction);
                    return this.respository.save(transactionCollectionUpdate)
                            .map(this.mapper::toTransaction);
                });
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Flux<Transaction> findAllTransactionsByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return this.respository.findAllByCreateAtBetween(dateFrom, dateTo)
                .map(this.mapper::toTransaction);
    }
}
