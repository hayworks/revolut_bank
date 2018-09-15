package com.revolut.bank;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.JacksonRequestConverterFunction;
import com.linecorp.armeria.server.annotation.JacksonResponseConverterFunction;
import com.revolut.bank.controller.AccountController;
import com.revolut.bank.controller.CustomerController;
import com.revolut.bank.controller.TransactionLogController;
import com.revolut.bank.dao.AccountDao;
import com.revolut.bank.dao.CustomerDao;
import com.revolut.bank.dao.TransactionLogDao;
import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.facade.CustomerFacade;
import com.revolut.bank.facade.TransactionLogFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        //objects build & Inject
        CustomerController customerController = new CustomerController(
                new CustomerFacade(
                        new CustomerDao("customer")
                )
        );

        TransactionLogDao transactionLogDao = new TransactionLogDao("transactionLog");

        AccountController accountController = new AccountController(
                new AccountFacade(
                        new AccountDao("account", transactionLogDao)
                )
        );

        TransactionLogController transactionLogController = new TransactionLogController(
                new TransactionLogFacade(
                        transactionLogDao
                )
        );

        // http
        final ServerBuilder builder = new ServerBuilder();

        // http-prepare
        builder
                .http(8080)
                .annotatedService(customerController, new JacksonRequestConverterFunction(), new JacksonResponseConverterFunction(), new BankExceptionHandler())
                .annotatedService(accountController, new JacksonRequestConverterFunction(), new JacksonResponseConverterFunction(), new BankExceptionHandler())
                .annotatedService(transactionLogController, new JacksonRequestConverterFunction(), new JacksonResponseConverterFunction(), new BankExceptionHandler());

        //server
        final Server server = builder.build();
        server.start().join();

        // ok
        LOGGER.info("Started server");

    }

}
