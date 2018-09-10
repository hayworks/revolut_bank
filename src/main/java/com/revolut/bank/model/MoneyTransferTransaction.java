package com.revolut.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
public class MoneyTransferTransaction extends BankEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Account senderAccount;

    @OneToOne(cascade = CascadeType.ALL)
    private Account receiverAccount;

    @Column
    private BigDecimal amount;

    @Column
    private Date createDate = new Date();

    @Enumerated(EnumType.STRING)
    private TransferStatus status = TransferStatus.NEW;

    @Setter
    private Date modifyDate = new Date();

    public MoneyTransferTransaction(Account senderAccount, Account receiverAccount, BigDecimal amount) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
    }

    public MoneyTransferTransaction() {
    }

    public Date getCreateDate() {
        return new Date(createDate.getTime());
    }

    public Date getModifyDate() {
        return new Date(modifyDate.getTime());
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
        this.modifyDate = new Date();
    }
}
