package com.revolut.bank.exception;

public class MoneyTransferException extends RuntimeException {

    public MoneyTransferException(String message) {
        super(message);
    }

    public MoneyTransferException(Exception e) {
        super(e);
    }
}
