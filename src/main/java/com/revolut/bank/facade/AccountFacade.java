package com.revolut.bank.facade;

import com.revolut.bank.dao.AccountDao;
import com.revolut.bank.exception.DataIntegrityException;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class AccountFacade {

    public AccountFacade(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private AccountDao accountDao;

    public void transferMoney(long senderAccountId, long receiverAccountId, BigDecimal bigDecimal) {

        if(!this.accountDao.exists(senderAccountId))
            throw new DataIntegrityException("sender account could not be found");

        if(!this.accountDao.exists(receiverAccountId))
            throw new DataIntegrityException("receiver account could not be found");

        this.accountDao.transferMoney(senderAccountId, receiverAccountId, bigDecimal);
    }
}
