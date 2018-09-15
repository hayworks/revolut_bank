package com.revolut.bank.controller;

import com.linecorp.armeria.server.annotation.*;
import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.model.MoneyTransferTransaction;

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
}
