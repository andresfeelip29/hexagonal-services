package com.co.technicaltest.transaction_service.domain.model;

import com.co.technicaltest.transaction_service.domain.exception.TransactionException;
import com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Object domain for transaction.
 *
 * @author andres on 2025/06/15.
 * @version 1.0.0
 */
@Builder
@Data
public class Transaction {

    private UUID id;
    private LocalDateTime createAt;
    private BigDecimal transactionValue;
    private BigDecimal initialBalanceAccountOrigin;
    private BigDecimal finalBalanceAccountOrigin;
    private BigDecimal initialBalanceAccountDestiny;
    private BigDecimal finalBalanceAccountDestiny;
    private UUID customerId;
    private String customerName;
    private String originAccountNumber;
    private String destinyAccountNumber;
    private TransactionStatus transactionStatus;
    private String descriptionMovement;


    /**
     * Method init new transaction, the creation date,
     * ID, and initial transaction status are created.
     *
     */
    public void initializeTransaction() {
        this.id = UUID.randomUUID();
        this.createAt = LocalDateTime.now();
        this.transactionStatus = TransactionStatus.TRANSACTION_INITIATED;
    }

    /**
     * Method for changing the status to approved when money
     * transactions in associated accounts are confirmed.
     *
     */
    public void approve() {
        if (this.transactionStatus != TransactionStatus.TRANSACTION_INITIATED) {
            throw new TransactionException("Transaccion no tiene un estado valido!");
        }
        this.transactionStatus = TransactionStatus.TRANSACTION_APPROVED;
    }


    /**
     * method to change the status if transactions are rejected or
     * accounts do not have sufficient funds.
     *
     */
    public void rejected() {
        if (this.transactionStatus != TransactionStatus.TRANSACTION_INITIATED) {
            throw new TransactionException("Transaccion no tiene un estado valido!");
        }
        this.transactionStatus = TransactionStatus.TRANSACTION_REJECTED;
    }

}
