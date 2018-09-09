package com.revolut.bank.model;

import com.revolut.bank.exception.AccountValidationException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Account extends BankEntity {

    @Column
    @Getter
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionLog> transactionLogs = new ArrayList<TransactionLog>();

    public Account(String name) {

        if(name == null)
            throw new AccountValidationException("Account name can not be null!!");

        this.name = name;
    }

    public Account(int id, String name) {

        super(id);

        if(name == null)
            throw new AccountValidationException("Account name can not be null!!");

        this.name = name;
    }

    public void addTransactionLog(TransactionLog log) {
        this.transactionLogs.add(log);
    }

    public List<TransactionLog> getTransactionLogs() {
        return Collections.unmodifiableList(transactionLogs);
    }

}
