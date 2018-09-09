package com.revolut.bank.model;

import com.revolut.bank.exception.AccountValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void should_account_able_to_have_name() {

        //given
        Account account = null;

        //when
        account = new Account("dummyName");

        //then
        assertNotNull(account.getName());
    }

    @Test(expected = AccountValidationException.class)
    public void should_throw_exception_when_assigning_null_name() {

        //given
        Account account = null;

        //when
        account = new Account(null);

        //then

    }

    @Test
    public void should_account_have_transaction_logs() {

        //given
        Account account = new Account("dummy");

        //when
        account.addTransactionLog(new TransactionLog(1, "description", 1,1 ));

        //then
        assertTrue(account.getTransactionLogs() != null && account.getTransactionLogs().size() > 0);

    }

}
