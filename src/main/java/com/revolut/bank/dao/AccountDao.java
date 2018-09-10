package com.revolut.bank.dao;

import com.revolut.bank.exception.InsufficientBalanceException;
import com.revolut.bank.exception.MoneyTransferException;
import com.revolut.bank.model.Account;
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

    public Account findById(long accountId) {

        return this.entityManager.find(Account.class, accountId);
    }

    public void transferMoney(long senderId, long receiverId, BigDecimal amount) throws MoneyTransferException {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Account> cq = cb.createQuery(Account.class);

        Root<Account> c = cq.from(Account.class);
        ParameterExpression<Long> sender = cb.parameter(Long.class);
        ParameterExpression<Long> receiver = cb.parameter(Long.class);
        cq.select(c).where(cb.or(cb.equal(c.get("id"), sender), cb.equal(c.get("id"), receiver)));

        TypedQuery<Account> query = entityManager.createQuery(cq);
        query.setParameter(sender, senderId);
        query.setParameter(receiver, receiverId);
        query.setLockMode(LockModeType.PESSIMISTIC_READ);

        entityManager.getTransaction().begin();

        try {

            List<Account> accounts = query.getResultList();

            //check amounts
            Account senderAccount = accounts.stream().filter(acc -> acc.getId() == senderId).findFirst().get();

            if (senderAccount.getAmount().compareTo(amount) == -1) {
                throw new InsufficientBalanceException("Insufficiant Funds");
            }

            Account receiverAccount = accounts.stream().filter(acc -> acc.getId() == receiverId).findFirst().get();

            senderAccount.setAmount(senderAccount.getAmount().subtract(amount));
            receiverAccount.setAmount(receiverAccount.getAmount().add(amount));

            this.persist(senderAccount);
            this.persist(receiverAccount);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new MoneyTransferException(e);
        }
    }
}
