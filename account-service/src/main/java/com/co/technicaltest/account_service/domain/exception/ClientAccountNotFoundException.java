package com.co.technicaltest.account_service.domain.exception;

import com.co.devsutest.common.exception.DomainException;

public class ClientAccountNotFoundException extends RuntimeException {

    public ClientAccountNotFoundException(String message) {
        super(message);
    }

    public ClientAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
