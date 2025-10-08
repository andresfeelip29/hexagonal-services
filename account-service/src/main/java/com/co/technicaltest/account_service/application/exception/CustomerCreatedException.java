package com.co.technicaltest.account_service.application.exception;

public class CustomerCreatedException extends RuntimeException{
    public CustomerCreatedException(String message) {
        super(message);
    }
}
