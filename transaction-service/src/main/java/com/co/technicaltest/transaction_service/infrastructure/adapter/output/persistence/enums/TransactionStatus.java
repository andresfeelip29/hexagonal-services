package com.co.technicaltest.transaction_service.infrastructure.adapter.output.persistence.enums;

import com.co.technicaltest.transaction_service.infrastructure.exception.TransactionStatusExepcion;
import com.co.technicaltest.transaction_service.infrastructure.shared.enums.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TransactionStatus {


    TRANSACTION_INITIATED("Transaccion iniciada"),
    TRANSACTION_APPROVED("Transaccion aprovada"),
    TRANSACTION_REJECTED("Transaccion rechazada");

    private final String type;

    TransactionStatus(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    @JsonCreator
    public static TransactionStatus forValues(String value) {
        return Stream.of(TransactionStatus.values())
                .filter(enumValue -> enumValue.type.equals(value))
                .findFirst()
                .orElseThrow(() -> new TransactionStatusExepcion(String.format(ExceptionMessage.INCORRECT_TRANSACTION_STATUS.getMessage(), value)) );
    }

}
