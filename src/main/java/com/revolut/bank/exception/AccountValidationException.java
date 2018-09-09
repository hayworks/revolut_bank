package com.revolut.bank.exception;

public class AccountValidationException extends RuntimeException {

    public AccountValidationException(String message) {
        super(message);
    }
}
