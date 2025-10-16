package com.co.technicaltest.transaction_service.infrastructure.mapper;

import com.co.technicaltest.transaction_service.domain.model.Transaction;
import com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.collections.TransactionCollection;
import org.springframework.stereotype.Component;

/**
 * Class to mapper collection information and persist it in a database.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class TransactionMapper {


    public Transaction toTransaction(TransactionCollection transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .createAt(transaction.getCreateAt())
                .transactionValue(transaction.getTransactionValue())
                .initialBalanceAccountOrigin(transaction.getInitialBalanceAccountOrigin())
                .finalBalanceAccountOrigin(transaction.getFinalBalanceAccountOrigin())
                .initialBalanceAccountDestiny(transaction.getInitialBalanceAccountDestiny())
                .finalBalanceAccountDestiny(transaction.getFinalBalanceAccountDestiny())
                .customerName(transaction.getCustomerName())
                .customerId(transaction.getCustomerId())
                .originAccountNumber(transaction.getOriginAccountNumber())
                .destinyAccountNumber(transaction.getDestinyAccountNumber())
                .transactionStatus(transaction.getTransactionStatus())
                .descriptionMovement(transaction.getDescriptionMovement())
                .build();
    }

    public TransactionCollection toTransactionCollection(Transaction transaction) {
        return TransactionCollection.builder()
                .id(transaction.getId())
                .createAt(transaction.getCreateAt())
                .transactionValue(transaction.getTransactionValue())
                .initialBalanceAccountOrigin(transaction.getInitialBalanceAccountOrigin())
                .finalBalanceAccountOrigin(transaction.getFinalBalanceAccountOrigin())
                .initialBalanceAccountDestiny(transaction.getInitialBalanceAccountDestiny())
                .finalBalanceAccountDestiny(transaction.getFinalBalanceAccountDestiny())
                .customerName(transaction.getCustomerName())
                .customerId(transaction.getCustomerId())
                .originAccountNumber(transaction.getOriginAccountNumber())
                .destinyAccountNumber(transaction.getDestinyAccountNumber())
                .transactionStatus(transaction.getTransactionStatus())
                .descriptionMovement(transaction.getDescriptionMovement())
                .build();

    }

    public TransactionCollection toUpdateTransactionCollection(TransactionCollection transactionCollection,
                                                               Transaction transaction) {
        transactionCollection.setInitialBalanceAccountOrigin(transaction.getInitialBalanceAccountOrigin());
        transactionCollection.setInitialBalanceAccountDestiny(transaction.getInitialBalanceAccountDestiny());
        transactionCollection.setFinalBalanceAccountOrigin(transaction.getFinalBalanceAccountOrigin());
        transactionCollection.setFinalBalanceAccountDestiny(transaction.getFinalBalanceAccountDestiny());
        transactionCollection.setCustomerName(transaction.getCustomerName());
        transactionCollection.setCustomerId(transaction.getCustomerId());
        transactionCollection.setTransactionStatus(transaction.getTransactionStatus());
        transactionCollection.setDescriptionMovement(transaction.getDescriptionMovement());
        return transactionCollection;

    }

}
