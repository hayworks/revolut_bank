package com.revolut.bank.controller;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.annotation.JacksonRequestConverterFunction;
import com.linecorp.armeria.server.annotation.JacksonResponseConverterFunction;
import com.revolut.bank.BankExceptionHandler;
import com.revolut.bank.dao.AccountDao;
import com.revolut.bank.dao.CustomerDao;
import com.revolut.bank.dao.TransactionLogDao;
import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.facade.CustomerFacade;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.Customer;
import com.revolut.bank.model.MoneyTransferTransaction;
import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class AccountControllerIntegrationTest {

    private Server server;

    private Account acc1, acc2;

    @Before
    public void before() {

        TransactionLogDao transactionLogDao = new TransactionLogDao("transactionLog");

        AccountController accountController = new AccountController(
                new AccountFacade(
                        new AccountDao("account", transactionLogDao)
                )
        );

        CustomerFacade customerFacade = new CustomerFacade(
                                                                new CustomerDao("customer")
                                                        );

        //create customer, account and add cash to account
        Customer custJohnDoe = customerFacade.createNew(new Customer("John Doe"));
        custJohnDoe = customerFacade.createNewAccountForCustomer(custJohnDoe.getId(), "private account");
        acc1 = custJohnDoe.getAccounts().get(0);
        acc1 = accountController.getAccountFacade().addMoney(acc1.getId(), new BigDecimal(50));

        //create customer, account
        Customer custMaryJane = customerFacade.createNew(new Customer("Mary Jane"));
        custMaryJane = customerFacade.createNewAccountForCustomer(custMaryJane.getId(), "private account");
        acc2 = custMaryJane.getAccounts().get(0);

        // http
        final ServerBuilder builder = new ServerBuilder();

        // http-prepare
        builder
                .http(8080)
                .annotatedService(accountController, new JacksonRequestConverterFunction(), new JacksonResponseConverterFunction(), new BankExceptionHandler());

        //server
        server = builder.build();
        server.start().join();
    }

    @After
    public void after() {
        server.stop();
    }

    @Test
    public void should_transfer_money_successfully() {

        given().
                contentType(ContentType.JSON).
                body(new MoneyTransferTransaction(acc1.getId() ,acc2.getId(), new BigDecimal(20))).
        when().
                put("/account").
                then().
                statusCode(204);

    }

    @Test
    public void should_add_money_to_account_successfully() {

        Account account = given().
                                pathParam("accountId", acc1.getId()).
                                contentType(ContentType.JSON).
                                body(50).
                        when().
                                put("http://localhost:8080/account/{accountId}").
                        then().
                                statusCode(200).
                                extract().response().as(Account.class);

        assertEquals(account.getId(), acc1.getId());
        assertEquals(acc1.getAmount(), account.getAmount());
    }

}
