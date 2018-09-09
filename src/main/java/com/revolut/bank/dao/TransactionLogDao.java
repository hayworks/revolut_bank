package com.revolut.bank.dao;

import com.revolut.bank.model.TransactionLog;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Setter
public class TransactionLogDao {

    private EntityManager entityManager;

    public void persist(TransactionLog transactionLog) {
        this.entityManager.persist(transactionLog);
    }

    public List<TransactionLog> findBySenderIdAndReceiver(long senderId, long receiverId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TransactionLog> cq = cb.createQuery(TransactionLog.class);

        Root<TransactionLog> c = cq.from(TransactionLog.class);
        ParameterExpression<Long> sender = cb.parameter(Long.class);
        ParameterExpression<Long> receiver = cb.parameter(Long.class);
        cq.select(c).where(cb.and(cb.equal(c.get("senderId"), sender),cb.equal(c.get("receiverId"), receiver)));

        TypedQuery<TransactionLog> query = entityManager.createQuery(cq);
        query.setParameter(sender, senderId);
        query.setParameter(receiver, receiverId);
        return query.getResultList();

    }

}
