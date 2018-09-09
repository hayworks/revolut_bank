package com.revolut.bank.exception;

public class TransactionLogValidationException extends RuntimeException {

    public TransactionLogValidationException(String message) {
        super(message);
    }
}
