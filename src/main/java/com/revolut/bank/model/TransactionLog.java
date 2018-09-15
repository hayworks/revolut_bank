package com.revolut.bank.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
public class TransactionLog extends BankEntity {

    @Column
    private long senderId;

    @Column(nullable = false)
    private long receiverId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column
    private Date createDate = new Date();

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    public TransactionLog(MoneyTransferTransaction moneyTransferTransaction, TransferStatus transferStatus) {
        this.senderId = moneyTransferTransaction.getSenderAccountId();
        this.receiverId = moneyTransferTransaction.getReceiverAccountId();
        this.amount = moneyTransferTransaction.getAmount();
        this.status = transferStatus;
    }

    public TransactionLog(long senderId, long receiverId, BigDecimal amount, TransferStatus transferStatus) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.status = transferStatus;
    }

    protected TransactionLog() {
    }
}
