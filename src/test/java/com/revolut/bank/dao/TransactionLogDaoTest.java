package com.revolut.bank.dao;

import com.revolut.bank.model.TransactionLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

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
        transactionLogDao.persist(new TransactionLog(1, "", 1, 1));

        //then
        assertTrue(true);
    }

    @Test
    public void should_get_transaction_logs_of_seller() {

        //given
        transactionLogDao.persist(new TransactionLog(1, "", 1, 1));

        //when
        List<TransactionLog> logs = this.transactionLogDao.findBySenderIdAndReceiver(1,1);

        //then
        assertNotNull(logs);
    }

}
