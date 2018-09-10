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

    @Test
    public void should_have_amount() {

        //given
        Account account = new Account("dummyName");;

        //when


        //then
        assertEquals(0, account.getAmount().intValue());

    }

    @Test(expected = AccountValidationException.class)
    public void should_throw_exception_when_assigning_null_name() {

        //given
        Account account = null;

        //when
        account = new Account(null);

        //then

    }


}
