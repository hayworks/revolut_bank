package com.revolut.bank.dao;

import com.revolut.bank.model.Account;
import lombok.Setter;

@Setter
public class AccountDao extends BaseDao<Account> {

    public Account findById(long accountId) {

        return this.entityManager.find(Account.class, accountId);
    }
}
