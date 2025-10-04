package com.co.technicaltest.account_service.domain.exception;



public class BalanceNegativeException extends RuntimeException {

    public BalanceNegativeException(String message) {
        super(message);
    }

    public BalanceNegativeException(String message, Throwable cause) {
        super(message, cause);
    }
}
