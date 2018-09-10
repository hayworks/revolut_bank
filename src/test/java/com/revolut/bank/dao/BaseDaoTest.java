package com.revolut.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class BaseDaoTest {

    protected EntityManager entityManager;

    public void before(String persistanceUnitName) {
        entityManager = Persistence.createEntityManagerFactory(persistanceUnitName).createEntityManager();
    }

}
