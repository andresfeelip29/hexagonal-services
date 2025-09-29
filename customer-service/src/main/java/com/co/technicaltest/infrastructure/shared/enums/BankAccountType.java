package com.co.technicaltest.infrastructure.shared.enums;

import com.co.technicaltest.application.exception.BankAccountException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * Enums for account types.
 *
 * @author andres on 2025/09/28.
 * @version 1.0.0
 */
public enum BankAccountType {

    SAVINGS_ACCOUNT("Ahorro"),
    CHECKING_ACCOUNT("Corriente");

    private final String type;

    BankAccountType(String type) {
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
    public static BankAccountType forValues(String value) {
        return Stream.of(BankAccountType.values())
                .filter(enumValue -> enumValue.type.equals(value))
                .findFirst()
                .orElseThrow(() -> new BankAccountException(String.format(ExceptionMessage.INCORRECT_BANK_ACCOUNT.getMessage(), value)) );
    }
}
