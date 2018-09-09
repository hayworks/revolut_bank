package com.revolut.bank.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class MoneyTransferTransactionTest {

    @Test
    public void should_transaction_include_sender_receiver_accounts_and_amount() {

        //given
        MoneyTransferTransaction moneyTransferTransaction = null;

        //when
        moneyTransferTransaction = new MoneyTransferTransaction(new Account("sender"),
                new Account("receiver"),
                new BigDecimal("10"));

        //then
        assertNotNull(moneyTransferTransaction.getSenderAccount());
        assertNotNull(moneyTransferTransaction.getReceiverAccount());
        assertNotNull(moneyTransferTransaction.getAmount());

    }

    @Test
    public void should_have_a_creation_date() {

        //given
        MoneyTransferTransaction moneyTransferTransaction = null;

        //when
        moneyTransferTransaction = new MoneyTransferTransaction(new Account("sender"),
                new Account("receiver"),
                new BigDecimal("10"));

        //then
        assertNotNull(moneyTransferTransaction.getCreateDate());
    }

    @Test
    public void should_have_a_modification_date() {

        //given
        MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction(new Account("sender"),
                new Account("receiver"),
                new BigDecimal("10"));

        //when
        moneyTransferTransaction.setModifyDate(new Date());

        //then
        assertNotNull(moneyTransferTransaction.getModifyDate());
    }

    @Test
    public void should_have_a_default_state() {

        //given
        MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction(new Account("sender"),
                new Account("receiver"),
                new BigDecimal("10"));

        //when

        //then
        assertEquals(moneyTransferTransaction.getStatus(), TransferStatus.NEW);
    }

}
