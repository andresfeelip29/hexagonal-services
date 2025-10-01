package com.co.technicaltest.account_service.domain.exception;

import com.co.devsutest.common.exception.DomainException;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
