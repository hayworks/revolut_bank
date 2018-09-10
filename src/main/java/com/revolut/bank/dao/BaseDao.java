package com.revolut.bank.dao;

import lombok.Setter;

import javax.persistence.EntityManager;

@Setter
public abstract class BaseDao<T> {

    protected EntityManager entityManager;

    public T persist(T item) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(item);
        this.entityManager.getTransaction().commit();
        return item;
    }


}
