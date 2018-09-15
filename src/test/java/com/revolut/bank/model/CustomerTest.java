package com.revolut.bank.model;

import com.revolut.bank.exception.CustomerValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void should_customer_have_name() {
        //given

        //when
        Customer customer = new Customer("John Doe");

        //then
        assertNotNull(customer.getName());
        assertTrue("John Doe".equals(customer.getName()));
    }

    @Test(expected = CustomerValidationException.class)
    public void should_customer_throw_exception_with_null_name() {

        //given
        Customer customer = null;

        //when
        new Customer(null);

        //then

    }

    @Test(expected = CustomerValidationException.class)
    public void should_customer_with_id_throw_exception_with_null_name() {

        //given
        Customer customer = null;

        //when
        new Customer(1, null);

        //then

    }

    @Test
    public void should_customer_can_have_multiple_account() {
        //given
        Customer customer = new Customer("john doe");

        //when
        customer.addAccount(new Account("dummy1 account"));
        customer.addAccount(new Account("dummy2 account"));

        //then
        assertNotNull(customer.getAccounts());
        assertEquals(customer.getAccounts().size(), 2);
    }


}
