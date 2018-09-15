package com.revolut.bank.controller;

import com.revolut.bank.facade.CustomerFacade;
import com.revolut.bank.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerFacade customerFacade;

    @Test
    public void should_create_new_customer() {

        //given
        Customer customer = new Customer(5, "John Doe");
        when(customerFacade.createNew(any(Customer.class))).thenReturn(customer);

        //when
        customer = this.customerController.createNew("John Doe");

        //then
        verify(customerFacade).createNew(any(Customer.class));
        assertNotNull(customer);
        assertEquals(customer.getId() , 5);

    }

    @Test
    public void should_get_customer_by_id() {

        //given
        Customer customer = new Customer(5, "John Doe");
        when(customerFacade.findById(5)).thenReturn(customer);

        //when
        customer = this.customerController.getCustomer(5);

        //then
        verify(customerFacade).findById(anyLong());
        assertNotNull(customer);

    }

    @Test
    public void should_create_new_account_for_customer() {

        //given
        when(customerFacade.createNewAccountForCustomer(anyLong(), anyString())).thenReturn(new Customer());

        //when
        Customer cust = this.customerController.newAccountForCustomer(5L, "private account");

        //then
        verify(customerFacade).createNewAccountForCustomer(5, "private account");
        assertNotNull(cust);

    }

    @Test
    public void should_delete_account_of_customer() {

        //given
        when(this.customerFacade.deleteAccountFromCustomer(anyLong(), anyLong())).thenReturn(new Customer("dummy"));

        //when
        Customer cust = this.customerController.deleteAccountFromCustomer(1, 1);

        //then
        verify(customerFacade).deleteAccountFromCustomer(anyLong(), anyLong());
        assertEquals(cust.getName(), "dummy");

    }

}
