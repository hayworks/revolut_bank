package com.revolut.bank.exception;

public class InsufficientBalanceException extends MoneyTransferException {

    public InsufficientBalanceException(String message) {
        super(message);
    }

}
