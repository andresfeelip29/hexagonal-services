package com.co.technicaltest.transaction_service.application.usecase;

import com.co.technicaltest.transaction_service.application.dto.TransactionRequestDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseStatusDTO;
import com.co.technicaltest.transaction_service.application.mapper.TransactionDTOMapper;
import com.co.technicaltest.transaction_service.domain.model.Transaction;
import com.co.technicaltest.transaction_service.domain.port.input.TransactionUseCase;
import com.co.technicaltest.transaction_service.domain.port.output.TransactionRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Use case port implemetation for transaction.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class TransactionService implements TransactionUseCase {


    private final TransactionRepositoryPort repository;

    private final TransactionDTOMapper mapper;

    public TransactionService(TransactionRepositoryPort repository,
                              TransactionDTOMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Flux<TransactionResponseDTO> findAllTransactions() {


        log.info("Metodo: {}, para obtener todos las transacciones",
                "[findAllTransactions]");

        return this.repository.findAllTransactions()
                .map(mapper::toTransactionResponseDTO);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Mono<TransactionResponseDTO> findTransactionById(UUID id) {

        log.info("Metodo: {}, para obtener trasaccion por id: {}",
                "[findAccountByAccountNumber]", id.toString());

        return this.repository.findTransactionById(id)
                .map(mapper::toTransactionResponseDTO);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public Mono<TransactionResponseStatusDTO> saveTransaction(TransactionRequestDTO transaction) {

        Transaction transactionCreate = this.mapper.toTransaction(transaction);

        transactionCreate.initializeTransaction();

        log.info("Metodo: {}, para guardar transaccion con numero generado: {}",
                "[saveAccount]", transactionCreate.getId().toString());

        return this.repository.saveTransaction(transactionCreate)
                .map(mapper::toTransactionResponseStatusDTO);
    }


    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Flux<TransactionResponseDTO> findAllTransactionsByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {

        log.info("Metodo: {}, para generar un reporte de todas las transacciones en rango de fecha de: {}  hasta :{}",
                "[findAllTransactions]", dateFrom.toString(), dateTo.toString());

        return this.repository.findAllTransactionsByDateRange(dateFrom, dateTo)
                .map(mapper::toTransactionResponseDTO);
    }
}
