package com.revolut.bank.model;

import com.revolut.bank.exception.TransactionLogValidationException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransactionLogTest {

    @Test
    public void should_log_contain_message() {

        //given
        TransactionLog log = null;

        //when
        log = new TransactionLog("dummy message", 1, 1);

        //then
        assertNotNull(log.getMessage());

    }

    @Test(expected = TransactionLogValidationException.class)
    public void should_log_contain_throw_exception_when_message_is_null() {

        //given
        TransactionLog log = null;

        //when
        log = new TransactionLog((String)null, 1, 1);

        //then

    }

    @Test
    public void should_be_constructed_with_money_transfer_transaction() {
        //given
        MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction(new Account("sender"),
                new Account("receiver"),
                new BigDecimal("10"));

        //when
        TransactionLog transactionLog = new TransactionLog(moneyTransferTransaction);

        //then
        assertNotNull(transactionLog.getMessage());
    }

}