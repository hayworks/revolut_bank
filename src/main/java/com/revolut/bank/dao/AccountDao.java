package com.revolut.bank.dao;

import com.revolut.bank.exception.InsufficientBalanceException;
import com.revolut.bank.exception.MoneyTransferException;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.TransactionLog;
import com.revolut.bank.model.TransferStatus;
import lombok.Setter;

import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Setter
public class AccountDao extends BaseDao<Account> {

    private TransactionLogDao transactionLogDao;

    public AccountDao(String persistenceUnitName, TransactionLogDao transactionLogDao) {
        super(persistenceUnitName);
        this.transactionLogDao = transactionLogDao;
    }

    public Account findById(long accountId) {

        return this.entityManager.find(Account.class, accountId);
    }

    public void transferMoney(long senderAccountId, long receiverAccountId, BigDecimal amount) throws MoneyTransferException {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Account> cq = cb.createQuery(Account.class);

        Root<Account> c = cq.from(Account.class);
        ParameterExpression<Long> sender = cb.parameter(Long.class);
        ParameterExpression<Long> receiver = cb.parameter(Long.class);
        cq.select(c).where(cb.or(cb.equal(c.get("id"), sender), cb.equal(c.get("id"), receiver)));

        TypedQuery<Account> query = entityManager.createQuery(cq);
        query.setParameter(sender, senderAccountId);
        query.setParameter(receiver, receiverAccountId);
        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        entityManager.getTransaction().begin();

        try {

            List<Account> accounts = query.getResultList();

            //check amounts
            Account senderAccount = accounts.stream().filter(acc -> acc.getId() == senderAccountId).findFirst().get();

            if (senderAccount.getAmount().compareTo(amount) == -1) {
                throw new InsufficientBalanceException("Insufficiant Funds");
            }

            Account receiverAccount = accounts.stream().filter(acc -> acc.getId() == receiverAccountId).findFirst().get();

            senderAccount.setAmount(senderAccount.getAmount().subtract(amount));
            receiverAccount.setAmount(receiverAccount.getAmount().add(amount));

            this.persist(senderAccount);
            this.persist(receiverAccount);

            transactionLogDao.persist(new TransactionLog(senderAccountId, receiverAccountId, amount, TransferStatus.SUCCESS));

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            transactionLogDao.persist(new TransactionLog(senderAccountId, receiverAccountId, amount, TransferStatus.FAIL));
            throw new MoneyTransferException(e);
        }
    }

    public boolean exists(long accountId) {
        return entityManager.find(Account.class, accountId) != null;
    }

    public Account addMoney(long accountId, BigDecimal amount) {

        Account acc;

        entityManager.getTransaction().begin();

        try {
            acc = this.entityManager.find(Account.class, accountId, LockModeType.PESSIMISTIC_WRITE);

            acc.setAmount(acc.getAmount().add(amount));

            acc = this.persist(acc);

            transactionLogDao.persist(new TransactionLog(accountId, accountId, amount, TransferStatus.SUCCESS));

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            transactionLogDao.persist(new TransactionLog(accountId, accountId, amount, TransferStatus.FAIL));
            throw new MoneyTransferException(e);
        }

        return acc;
    }
}
