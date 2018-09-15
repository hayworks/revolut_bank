package com.revolut.bank.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransactionLogTest {

    @Test
    public void should_be_constructed_with_money_transfer_transaction() {
        //given
        MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction(1L,
                2L,
                new BigDecimal("10"));

        //when
        TransactionLog transactionLog = new TransactionLog(moneyTransferTransaction, TransferStatus.SUCCESS);

        //then
        assertNotNull(transactionLog.getAmount());
        assertEquals(transactionLog.getReceiverId() , 2L);
        assertEquals(transactionLog.getSenderId(), 1L);
        assertNotNull(transactionLog.getCreateDate());
        assertEquals(transactionLog.getStatus(), TransferStatus.SUCCESS);
    }

    @Test
    public void should_be_constructed_with_raw_values() {

        //given
        long senderId = 1L;
        long receiverId = 2L;
        BigDecimal amount = new BigDecimal(10);

        //when
        TransactionLog transactionLog = new TransactionLog(senderId, receiverId,amount, TransferStatus.SUCCESS);

        //then
        assertNotNull(transactionLog.getAmount());
        assertEquals(transactionLog.getReceiverId() , 2L);
        assertEquals(transactionLog.getSenderId(), 1L);
        assertNotNull(transactionLog.getCreateDate());
        assertEquals(transactionLog.getStatus(), TransferStatus.SUCCESS);

    }

}