package com.revolut.bank.dao;

import com.revolut.bank.model.Account;
import com.revolut.bank.model.Customer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerDaoTest extends BaseDaoTest {

    private CustomerDao customerDao;

    @Before
    public void before() {
        super.before("customer");
        customerDao = new CustomerDao();
        customerDao.setEntityManager(entityManager);
    }

    @Test
    public void should_persist_a_new_customer() {

        //given
        Customer customer = new Customer("John Doe");
        customer.addAccount(new Account("dummy1"));
        customer.addAccount(new Account("dummy2"));

        //when
        customer = this.customerDao.persist(customer);

        //then
        assertNotNull(customer);
        assertNotNull(customer.getAccounts());
        assertEquals(2, customer.getAccounts().size());

    }

    @Test
    public void should_update_customer() {

        //given
        Customer customer = new Customer("John Doe");
        customer.addAccount(new Account("dummy1"));
        customer = this.customerDao.persist(customer);

        //when
        customer.addAccount(new Account("dummy2"));
        customer = this.customerDao.persist(customer);

        //then
        assertNotNull(customer);
        assertNotNull(customer.getAccounts());
        assertEquals(2, customer.getAccounts().size());

    }

    public void should_get_customer_by_id() {

        //given
        Customer customer = new Customer("John Doe");
        customer.addAccount(new Account("dummy1"));
        customer = this.customerDao.persist(customer);

        //when
        customer = this.customerDao.findById(customer.getId());

        //then
        assertNotNull(customer);

    }

}