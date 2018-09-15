package com.revolut.bank.dao;

import com.revolut.bank.model.Customer;

public class CustomerDao extends BaseDao<Customer> {

    public CustomerDao(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    public Customer findById(long id) {
        return entityManager.find(Customer.class, id);
    }

}
