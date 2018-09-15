package com.revolut.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyTransferTransaction{

    private Long senderAccountId;

    private Long receiverAccountId;

    private BigDecimal amount;

}
