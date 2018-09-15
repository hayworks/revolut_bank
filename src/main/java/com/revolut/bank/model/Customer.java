package com.revolut.bank.model;

import com.revolut.bank.exception.CustomerValidationException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Customer extends BankEntity {

    @Column
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

    public Customer(String name) {

        if(name == null)
            throw new CustomerValidationException("Customer name can not be null1");

        this.name = name;
    }

    public Customer(long id, String name) {

        super(id);

        if(name == null)
            throw new CustomerValidationException("Customer name can not be null1");

        this.name = name;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }


}
