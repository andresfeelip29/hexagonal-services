package com.co.technicaltest.transaction_service.application.mapper;

import com.co.technicaltest.transaction_service.application.dto.TransactionRequestDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseDTO;
import com.co.technicaltest.transaction_service.application.dto.TransactionResponseStatusDTO;
import com.co.technicaltest.transaction_service.domain.model.Transaction;
import org.springframework.stereotype.Component;

/**
 * Class to mapper collection information and persist it in a database.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Component
public class TransactionDTOMapper {


    public TransactionResponseDTO toTransactionResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .accountOriginNumber(transaction.getOriginAccountNumber())
                .accountDestinyNumber(transaction.getDestinyAccountNumber())
                .finalBalanceAccountOrigin(transaction.getFinalBalanceAccountOrigin())
                .finalBalanceAccountDestiny(transaction.getFinalBalanceAccountDestiny())
                .transactionStatus(transaction.getTransactionStatus().getType())
                .descriptionMovement(transaction.getDescriptionMovement())
                .transactionValue(transaction.getTransactionValue())
                .customerName(transaction.getCustomerName())
                .createAt(transaction.getCreateAt())
                .build();
    }

    public Transaction toTransaction(TransactionRequestDTO transactionResponseDTO) {
        return Transaction.builder()
                .transactionValue(transactionResponseDTO.transactionValue())
                .originAccountNumber(transactionResponseDTO.accountOriginNumber())
                .destinyAccountNumber(transactionResponseDTO.accountDestinyNumber())
                .build();
    }

    public TransactionResponseStatusDTO toTransactionResponseStatusDTO(Transaction transaction) {
        return TransactionResponseStatusDTO.builder()
                .createAt(transaction.getCreateAt())
                .transactionStatus(transaction.getTransactionStatus().getType())
                .id(transaction.getId())
                .descriptionMovement(transaction.getDescriptionMovement())
                .build();
    }
}
