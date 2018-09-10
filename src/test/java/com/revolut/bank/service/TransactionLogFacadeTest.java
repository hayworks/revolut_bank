package com.revolut.bank.service;

import com.revolut.bank.dao.TransactionLogDao;
import com.revolut.bank.facade.TransactionLogFacade;
import com.revolut.bank.model.TransactionLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionLogFacadeTest {

    @InjectMocks
    private TransactionLogFacade transactionLogFacade;

    @Mock
    private TransactionLogDao transactionLogDao;

    @Test
    public void should_get_transaction_logs_of_sender_account() {

        //given
        List<TransactionLog> transactionLogs = new ArrayList<TransactionLog>() {{
            add(new TransactionLog("message1", 1, 2));
            add(new TransactionLog("message2", 1, 3));
        }};
        when(transactionLogDao.findBySenderId(anyLong())).thenReturn(transactionLogs);

        //when
        transactionLogs = transactionLogFacade.getLogsOfSender(new Long(1));

        //then
        assertEquals(transactionLogs.size(), 2);
    }

    @Test
    public void should_get_transaction_logs_of_receiver_account() {

        //given
        List<TransactionLog> transactionLogs = new ArrayList<TransactionLog>() {{
            add(new TransactionLog("message1", 1, 2));
            add(new TransactionLog("message2", 1, 3));
        }};
        when(transactionLogDao.findByReceiverId(anyLong())).thenReturn(transactionLogs);

        //when
        transactionLogs = transactionLogFacade.getLogsOfReceiver(new Long(1));

        //then
        assertEquals(transactionLogs.size(), 2);
    }

    @Test
    public void should_add_new_transaction_log() {

        //given
        TransactionLog transactionLog = new TransactionLog("message1", 1, 2);
        when(transactionLogDao.persist(transactionLog)).thenReturn(transactionLog);

        //when
        transactionLog = this.transactionLogFacade.persist(transactionLog);

        //then
        assertNotNull(transactionLog);

    }
}
