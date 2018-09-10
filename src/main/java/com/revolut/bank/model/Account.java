package com.revolut.bank.model;

import com.revolut.bank.exception.AccountValidationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Account extends BankEntity {

    @Column
    @Getter
    private String name;

    @Column
    @Getter
    @Setter
    private BigDecimal amount = new BigDecimal(0);

    public Account(String name) {

        if(name == null)
            throw new AccountValidationException("Account name can not be null!!");

        this.name = name;
    }

    public Account() {
    }

}
