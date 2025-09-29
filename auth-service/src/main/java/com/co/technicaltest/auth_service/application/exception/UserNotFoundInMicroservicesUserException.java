package com.co.technicaltest.auth_service.application.exception;

public class UserNotFoundInMicroservicesUserException extends RuntimeException {
    public UserNotFoundInMicroservicesUserException() {
        super();
    }
    public UserNotFoundInMicroservicesUserException(String message) {
        super(message);
    }
}
