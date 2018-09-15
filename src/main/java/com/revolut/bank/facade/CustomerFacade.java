package com.revolut.bank.facade;

import com.revolut.bank.dao.CustomerDao;
import com.revolut.bank.exception.DataIntegrityException;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.Customer;
import lombok.Setter;

import java.util.Optional;

@Setter
public class CustomerFacade {

    private CustomerDao customerDao;

    public CustomerFacade(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer createNew(Customer customer) {
        return this.customerDao.persist(customer);
    }

    public Customer findById(long id) {
        return this.customerDao.findById(id);
    }

    public Customer createNewAccountForCustomer(long customerId, String accountName) {

        Customer customer = this.customerDao.findById(customerId);

        if(customer == null)
            throw new DataIntegrityException("Customer not found with the id " + customerId);

        customer.addAccount(new Account(accountName));

        return this.customerDao.persist(customer);
    }

    public Customer deleteAccountFromCustomer(long customerId, long accountId) {

        Customer customer = this.customerDao.findById(customerId);

        if(customer == null)
            throw new DataIntegrityException("Customer not found with the id " + customerId);

        Optional<Account> account = customer.getAccounts().stream().filter(acc -> acc.getId() == accountId).findFirst();

        if(!account.isPresent())
            throw new DataIntegrityException("Account not found with the id " + account + " or it is not bound to customer " + customerId);

        customer.getAccounts().remove(account);

        return this.customerDao.persist(customer);
    }
}
