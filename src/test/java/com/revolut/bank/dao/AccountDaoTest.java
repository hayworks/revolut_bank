package com.revolut.bank.dao;

import com.revolut.bank.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountDaoTest extends BaseDaoTest {

    private AccountDao accountDao;

    @Before
    public void before() {
        super.before("account");
        accountDao = new AccountDao();
        accountDao.setEntityManager(entityManager);
    }

    @Test
    public void should_persist_new_account() {

        //given

        //when
        Account account = this.accountDao.persist(new Account("dummyAccount"));

        //then
        assertNotNull(account);

    }

    @Test
    public void should_get_account_by_id() {

        //given
        Account account = this.accountDao.persist(new Account("dummyAccount"));

        //when
        Account acc = this.accountDao.findById(account.getId());

        //then
        assertNotNull(acc);

    }

    @Test
    public void should_update_account() {

        //given
        Account account = this.accountDao.persist(new Account("dummyAccount"));

        //when
        account.setAmount(new BigDecimal(20));
        account = this.accountDao.persist(account);
        account = this.accountDao.findById(account.getId());

        //then
        assertNotNull(account);
        assertEquals(account.getAmount().intValue(), 20);

    }

}