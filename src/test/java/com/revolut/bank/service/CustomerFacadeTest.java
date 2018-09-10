package com.revolut.bank.service;

import com.revolut.bank.dao.CustomerDao;
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
        customer.addAccount(new Account("dummy1"));
        customer.addAccount(new Account("dummy2"));
        when(this.customerDao.persist(any(Customer.class))).thenReturn(customer);

        //when
        customer = this.customerFacade.persist(customer);

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

}