package com.revolut.bank.dao;

import com.revolut.bank.model.TransactionLog;
import com.revolut.bank.model.TransferStatus;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Setter
public class TransactionLogDao extends BaseDao<TransactionLog> {

    public TransactionLogDao(String persistenceUnitName) {
        super(persistenceUnitName);
    }

    public List<TransactionLog> findBySenderId(long senderId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TransactionLog> cq = cb.createQuery(TransactionLog.class);

        Root<TransactionLog> c = cq.from(TransactionLog.class);
        ParameterExpression<Long> sender = cb.parameter(Long.class);
        cq.select(c).where(cb.and(cb.equal(c.get("senderId"), sender)));

        TypedQuery<TransactionLog> query = entityManager.createQuery(cq);
        query.setParameter(sender, senderId);

        //TODO: pagination can be added: https://www.baeldung.com/jpa-pagination

        return query.getResultList();

    }

    public List<TransactionLog> findByReceiverId(long receiverId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TransactionLog> cq = cb.createQuery(TransactionLog.class);

        Root<TransactionLog> c = cq.from(TransactionLog.class);
        ParameterExpression<Long> receiver = cb.parameter(Long.class);
        cq.select(c).where(cb.and(cb.equal(c.get("receiverId"), receiver), cb.equal(c.get("status"), TransferStatus.SUCCESS)));

        TypedQuery<TransactionLog> query = entityManager.createQuery(cq);
        query.setParameter(receiver, receiverId);

        //TODO: pagination can be added: https://www.baeldung.com/jpa-pagination

        return query.getResultList();

    }

}
