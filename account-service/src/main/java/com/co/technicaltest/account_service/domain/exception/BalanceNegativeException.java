package com.co.technicaltest.account_service.domain.exception;

import com.co.devsutest.common.exception.DomainException;

public class BalanceNegativeException extends RuntimeException {

    public BalanceNegativeException(String message) {
        super(message);
    }

    public BalanceNegativeException(String message, Throwable cause) {
        super(message, cause);
    }
}
