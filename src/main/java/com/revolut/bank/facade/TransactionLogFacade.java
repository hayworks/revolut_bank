package com.revolut.bank.facade;

import com.revolut.bank.dao.TransactionLogDao;
import com.revolut.bank.model.TransactionLog;
import lombok.Setter;

import java.util.List;

@Setter
public class TransactionLogFacade {

    private TransactionLogDao transactionLogDao;

    public List<TransactionLog> getLogsOfSender(long senderId) {
        return transactionLogDao.findBySenderId(senderId);
    }

    public List<TransactionLog> getLogsOfReceiver(long receiverId) {
        return transactionLogDao.findByReceiverId(receiverId);
    }

    public TransactionLog persist(TransactionLog transactionLog) {
        return this.transactionLogDao.persist(transactionLog);
    }

}
