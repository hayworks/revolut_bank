package com.revolut.bank.controller;

import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.model.Account;
import com.revolut.bank.model.MoneyTransferTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountFacade accountFacade;

    @Test
    public void should_transfer_money_successfully() {

        //given
        doNothing().when(accountFacade).transferMoney(anyLong(), anyLong(), any(BigDecimal.class));

        //when
        accountController.moneyTransfer(new MoneyTransferTransaction(1L ,2L, new BigDecimal(20)));

        //then
        verify(accountFacade).transferMoney(anyLong(), anyLong(), any(BigDecimal.class));

    }

    @Test
    public void should_add_money_successfully() {

        //given
        when(accountFacade.addMoney(anyLong(), any(BigDecimal.class))).thenReturn(new Account());

        //when
        accountController.addMoney(1L, 20);

        //then
        verify(accountFacade).addMoney(anyLong(), any(BigDecimal.class));

    }

}
