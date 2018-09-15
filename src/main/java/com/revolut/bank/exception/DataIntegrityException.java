package com.revolut.bank.exception;

public class DataIntegrityException extends RuntimeException {

    public  DataIntegrityException(String message) {
        super(message);
    }
}
