package com.revolut.bank.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MoneyTransferTransactionTest {

    @Test
    public void should_transaction_include_sender_receiver_accounts_and_amount() {

        //given
        MoneyTransferTransaction moneyTransferTransaction = null;

        //when
        moneyTransferTransaction = new MoneyTransferTransaction(1L ,2L, new BigDecimal("10"));

        //then
        assertEquals(moneyTransferTransaction.getSenderAccountId().longValue(), 1L);
        assertEquals(moneyTransferTransaction.getReceiverAccountId().longValue(), 2L);
        assertNotNull(moneyTransferTransaction.getAmount());

    }
}
