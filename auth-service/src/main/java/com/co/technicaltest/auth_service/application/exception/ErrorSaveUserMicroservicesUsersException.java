package com.co.technicaltest.auth_service.application.exception;

public class ErrorSaveUserMicroservicesUsersException extends RuntimeException {

    public ErrorSaveUserMicroservicesUsersException(String message) {
        super(message);
    }

    public ErrorSaveUserMicroservicesUsersException(String message, Throwable cause) {
        super(message, cause);
    }
}
