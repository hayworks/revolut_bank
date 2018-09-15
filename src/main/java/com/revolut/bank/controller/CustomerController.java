package com.revolut.bank.controller;

import com.revolut.bank.facade.CustomerFacade;
import com.revolut.bank.model.Customer;

import com.linecorp.armeria.server.annotation.*;

public class CustomerController {

    private CustomerFacade customerFacade;

    public CustomerController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @Post("/customer")
    @Consumes("application/x-www-form-urlencoded")
    @ProducesJson
    public Customer createNew(@Param("name") String name) {
        return customerFacade.createNew(new Customer(name));
    }

    @Get("/customer/{id}")
    @ProducesJson
    public Customer getCustomer(@Param("id") long id) {
        return this.customerFacade.findById(id);
    }

    @Put("/customer/{customerId}")
    @Consumes("application/x-www-form-urlencoded")
    @ProducesJson
    public Customer newAccountForCustomer(@Param("customerId") Long customerId, @Param("accountName") String accountName) {
        return this.customerFacade.createNewAccountForCustomer(customerId, accountName);
    }

    @Delete("/customer/{customerId}/{accountId}")
    @ProducesJson
    public Customer deleteAccountFromCustomer(@Param("customerId") long customerId, @Param("accountId") long accountId) {
        return this.customerFacade.deleteAccountFromCustomer(customerId, accountId);
    }
}
