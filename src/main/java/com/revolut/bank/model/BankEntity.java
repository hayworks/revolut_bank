package com.revolut.bank.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    public BankEntity() {}

    public BankEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
