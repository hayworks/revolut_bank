package com.revolut.bank.facade;

import com.revolut.bank.dao.CustomerDao;
import com.revolut.bank.model.Customer;
import lombok.Setter;

@Setter
public class CustomerFacade {

    private CustomerDao customerDao;

    public Customer persist(Customer customer) {
        return this.customerDao.persist(customer);
    }

    public Customer findById(long id) {
        return this.customerDao.findById(id);
    }
}
