package com.revolut.bank.dao;

import com.revolut.bank.model.TransactionLog;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class TransactionLogDaoTest {

    private TransactionLogDao transactionLogDao;

    private EntityManager entityManager;

    @Before
    public void before() {
        entityManager = Persistence.createEntityManagerFactory("transactionLog").createEntityManager();
        transactionLogDao = new TransactionLogDao();
        transactionLogDao.setEntityManager(entityManager);
    }

    @Test
    public void should_persist_transaction_log_instance() {

        //given

        //when
        TransactionLog transactionLog = transactionLogDao.persist(new TransactionLog( "", 1, 1));

        //then
        assertNotNull(transactionLog);
    }

    @Test
    public void should_get_transaction_logs_of_sender() {

        //given
        transactionLogDao.persist(new TransactionLog("", 1, 1));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findBySenderId(1);

        //then
        assertNotNull(logs);
        assertEquals(logs.size(), 1);
    }

    @Test
    public void should_get_transaction_logs_of_receiver() {

        //given
        transactionLogDao.persist(new TransactionLog("", 1, 1));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findByReceiverId(1);

        //then
        assertNotNull(logs);
        assertEquals(logs.size(), 1);
    }

}
