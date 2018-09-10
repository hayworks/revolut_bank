package com.revolut.bank.exception;

public class MoneyTransferException extends Exception {

    public MoneyTransferException(String message) {
        super(message);
    }

    public MoneyTransferException(Exception e) {
        super(e);
    }
}
