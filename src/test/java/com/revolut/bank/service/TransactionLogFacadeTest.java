package com.revolut.bank.service;

import com.revolut.bank.dao.TransactionLogDao;
import com.revolut.bank.facade.TransactionLogFacade;
import com.revolut.bank.model.TransactionLog;
import com.revolut.bank.model.TransferStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
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
            add(new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.SUCCESS));
            add(new TransactionLog(1, 3, new BigDecimal(10), TransferStatus.SUCCESS));
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
            add(new TransactionLog(3, 1, new BigDecimal(10), TransferStatus.SUCCESS));
            add(new TransactionLog(2, 1, new BigDecimal(10), TransferStatus.SUCCESS));
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
        TransactionLog transactionLog = new TransactionLog(1, 2, new BigDecimal(10), TransferStatus.SUCCESS);
        when(transactionLogDao.persist(transactionLog)).thenReturn(transactionLog);

        //when
        transactionLog = this.transactionLogFacade.persist(transactionLog);

        //then
        assertNotNull(transactionLog);

    }
}
