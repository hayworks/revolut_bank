package com.revolut.bank.controller;

import com.linecorp.armeria.server.annotation.*;
import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.MoneyTransferTransaction;

import java.math.BigDecimal;

public class AccountController {

    private AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @Put("/account")
    @Consumes("application/json")
    public void moneyTransfer(@RequestObject() MoneyTransferTransaction moneyTransferTransaction) {
        accountFacade.transferMoney(moneyTransferTransaction.getSenderAccountId(),
                moneyTransferTransaction.getReceiverAccountId(),
                moneyTransferTransaction.getAmount());
    }

    @Put("/account/{accountId}")
    @ProducesJson
    public Account addMoney(@Param("accountId") long accountId, @Param("amount") double amount) {
        return this.accountFacade.addMoney(accountId, new BigDecimal(amount).setScale(2));
    }
}
