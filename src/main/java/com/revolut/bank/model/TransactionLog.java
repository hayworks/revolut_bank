package com.revolut.bank.model;

import com.revolut.bank.exception.TransactionLogValidationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TransactionLog extends BankEntity {

    @Column
    @Getter
    private String message;

    @Column
    private long senderId;

    @Column
    private long receiverId;

    public TransactionLog(MoneyTransferTransaction moneyTransferTransaction) {
        this.message = new StringBuilder().append("Sender:").append(moneyTransferTransaction.getSenderAccount().getId())
                .append(", Receiver:").append(moneyTransferTransaction.getReceiverAccount().getId())
                .append(", Amount:").append(moneyTransferTransaction.getAmount())
                .append(", CreateDate:").append(moneyTransferTransaction.getCreateDate())
                .append(", ModifyDate:").append(moneyTransferTransaction.getModifyDate())
                .toString();

        this.senderId = moneyTransferTransaction.getSenderAccount().getId();
        this.receiverId = moneyTransferTransaction.getReceiverAccount().getId();
    }

    public TransactionLog(long id, String message, long senderId, int receiverId) {

        super(id);

        if(message == null)
            throw new TransactionLogValidationException("Transaction logs should contain a message");

        this.message = message;

        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
