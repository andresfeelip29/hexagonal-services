package com.co.technicaltest.transaction_service.domain.port.input;

import com.co.technicaltest.transaction_service.application.dto.TransactionRequestDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseStatusDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Interface transaction use case.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface TransactionUseCase {

    /**
     * Method for find all transactions.
     *
     * @return Flux<TransactionResponseDTO> transactions list data DTO
     */
    Flux<TransactionResponseDTO> findAllTransactions();

    /**
     * Method for find transaction by id.
     *
     * @param id the transaction id.
     * @return Mono<TransactionResponseDTO> transaction data DTO.
     */
    Mono<TransactionResponseDTO> findTransactionById(UUID id);

    /**
     * Method for save transaction.
     *
     * @param transaction data transaction information for save.
     * @return Mono<TransactionResponseDTO> customer data DTO.
     */
    Mono<TransactionResponseStatusDTO> saveTransaction(TransactionRequestDTO transaction);


    /**
     * Method for find all transactions by date range.
     *
     * @param dateFrom date range from init.
     * @param dateTo   date range to end.
     * @return Flux<TransactionResponseDTO> transactions list data DTO.
     */
    Flux<TransactionResponseDTO> findAllTransactionsByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);
}
