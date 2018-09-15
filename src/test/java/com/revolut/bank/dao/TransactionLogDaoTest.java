package com.revolut.bank.dao;

import com.revolut.bank.model.TransactionLog;
import com.revolut.bank.model.TransferStatus;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionLogDaoTest {

    private TransactionLogDao transactionLogDao;

    @Before
    public void before() {
        transactionLogDao = new TransactionLogDao("transactionLog");
    }

    @Test
    public void should_persist_transaction_log_instance() {

        //given

        //when
        TransactionLog transactionLog = transactionLogDao.persist(new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.SUCCESS));

        //then
        assertNotNull(transactionLog);
    }

    @Test
    public void should_get_transaction_logs_of_sender() {

        //given
        transactionLogDao.persist(new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.SUCCESS));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findBySenderId(1);

        //then
        assertNotNull(logs);
        assertEquals(logs.size(), 1);
    }

    @Test
    public void should_get_transaction_logs_of_receiver() {

        //given
        transactionLogDao.persist(new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.SUCCESS));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findByReceiverId(2);

        //then
        assertNotNull(logs);
        assertEquals(logs.size(), 1);
    }

    @Test
    public void should_not_get_failed_transaction_logs_of_receiver() {

        //given
        transactionLogDao.persist(new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.FAIL));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findByReceiverId(2);

        //then
        assertNotNull(logs);
        assertEquals(logs.size(), 0);
    }

}
