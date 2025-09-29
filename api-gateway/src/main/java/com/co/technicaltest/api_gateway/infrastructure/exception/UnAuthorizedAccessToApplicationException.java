package com.co.technicaltest.api_gateway.infrastructure.exception;

public class UnAuthorizedAccessToApplicationException extends RuntimeException {

    public UnAuthorizedAccessToApplicationException(String message) {
        super(message);
    }

    public UnAuthorizedAccessToApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
