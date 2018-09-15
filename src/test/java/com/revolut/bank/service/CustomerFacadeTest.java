package com.revolut.bank.service;

import com.revolut.bank.dao.CustomerDao;
import com.revolut.bank.exception.DataIntegrityException;
import com.revolut.bank.facade.CustomerFacade;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerFacadeTest {

    @InjectMocks
    private CustomerFacade customerFacade;

    @Mock
    private CustomerDao customerDao;

    @Test
    public void should_create_new_customer() {

        //when
        Customer customer = new Customer("John Doe");
        when(this.customerDao.persist(any(Customer.class))).thenReturn(customer);

        //when
        customer = this.customerFacade.createNew(customer);

        //then
        assertNotNull(customer);

    }

    @Test
    public void should_get_customer_by_id() {

        //given
        Customer customer = new Customer("John Doe");
        when(this.customerDao.findById(anyLong())).thenReturn(customer);

        //when
        customer = this.customerFacade.findById(customer.getId());

        //then
        assertNotNull(customer);

    }

    @Test
    public void should_add_new_account_to_customer() {

        //given
        when(customerFacade.findById(1)).thenReturn(new Customer(1, "John Doe"));
        when(customerFacade.createNew(any(Customer.class))).thenReturn(new Customer(1, "John Doe"));

        //when
        Customer customer = this.customerFacade.createNewAccountForCustomer(1L, "private account");

        //then
        verify(customerDao).persist(any(Customer.class));
        assertNotNull(customer);

    }

    @Test(expected = DataIntegrityException.class)
    public void should_throw_exception_when_customer_is_missing() {

        //given
        when(customerFacade.findById(1)).thenReturn(null);

        //when
        this.customerFacade.createNewAccountForCustomer(1L, "private account");

        //then

    }

    @Test
    public void should_remove_account_from_customer() {

        //given
        Customer cust = new Customer(1, "John Doe");
        cust.addAccount(new Account(2, "dummy"));
        when(customerFacade.findById(1)).thenReturn(cust);
        when(customerFacade.createNew(any(Customer.class))).thenReturn(new Customer(1, "John Doe"));

        //when
        Customer customer = this.customerFacade.deleteAccountFromCustomer(1L, 2L);

        //then
        verify(customerDao).persist(any(Customer.class));
        assertNotEquals(customer.getAccounts().size(), 1);

    }

    @Test(expected = DataIntegrityException.class)
    public void should_throw_exception_while_customer_is_missing() {

        //given

        //when
        this.customerFacade.deleteAccountFromCustomer(1L, 2L);

        //then


    }

    @Test(expected = DataIntegrityException.class)
    public void should_throw_exception_while_remove_missing_account() {

        //given
        Customer cust = new Customer(1, "John Doe");
        cust.addAccount(new Account(2, "dummy"));
        when(customerFacade.findById(1)).thenReturn(cust);

        //when
        this.customerFacade.deleteAccountFromCustomer(1L, 5L);

        //then


    }
}