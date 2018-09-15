package com.revolut.bank.model;

import com.revolut.bank.exception.AccountValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
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

    public Account(long id, String name) {

        this(name);
        this.id = id;
    }

}
