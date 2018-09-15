package com.revolut.bank.controller;

import com.linecorp.armeria.server.annotation.*;
import com.revolut.bank.facade.TransactionLogFacade;
import com.revolut.bank.model.TransactionLog;

import java.util.List;

@ResponseConverter(JacksonResponseConverterFunction.class)
public class TransactionLogController {

    private TransactionLogFacade transactionLogFacade;

    public TransactionLogController(TransactionLogFacade transactionLogFacade) {
        this.transactionLogFacade = transactionLogFacade;
    }

    @Get("/transactions/sender/{accountId}")
    @Produces("application/json")
    public List<TransactionLog> getSenderLogs(@Param("accountId") long accountId) {
        return this.transactionLogFacade.getLogsOfSender(accountId);
    }

    @Get("/transactions/receiver/{accountId}")
    @Produces("application/json")
    public List<TransactionLog> getReceiverLogs(@Param("accountId") long accountId) {
        return this.transactionLogFacade.getLogsOfReceiver(accountId);
    }

}
