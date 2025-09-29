package com.co.technicaltest.api_gateway.infrastructure.exception;

public class MissingAuthorizationHeaderException extends RuntimeException {

    public MissingAuthorizationHeaderException(String message) {
        super(message);
    }

    public MissingAuthorizationHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
