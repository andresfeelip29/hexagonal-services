package com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.transaction;

import com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.collections.TransactionCollection;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Spring Reactive MongoDB transaction persistence.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface SpringDataTransactionRespository extends ReactiveMongoRepository<TransactionCollection, UUID> {


    Flux<TransactionCollection> findAllByCreateAtBetween(LocalDateTime start,
                                                         LocalDateTime end);

}
