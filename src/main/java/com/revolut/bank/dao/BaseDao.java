package com.revolut.bank.dao;

import lombok.Setter;

import javax.persistence.EntityManager;

@Setter
public abstract class BaseDao<T> {

    protected EntityManager entityManager;

    public T persist(T item) {

        boolean active = !this.entityManager.getTransaction().isActive();

        if(active)
            this.entityManager.getTransaction().begin();

        this.entityManager.persist(item);

        if(active)
            this.entityManager.getTransaction().commit();

        return item;
    }


}
