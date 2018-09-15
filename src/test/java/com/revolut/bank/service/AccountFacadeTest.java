package com.revolut.bank.service;

import com.revolut.bank.dao.AccountDao;
import com.revolut.bank.exception.DataIntegrityException;
import com.revolut.bank.facade.AccountFacade;
import com.revolut.bank.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountFacadeTest {

    @InjectMocks
    private AccountFacade accountFacade;

    @Mock
    private AccountDao accountDao;

    @Test
    public void should_be_able_to_transfer_money() {

        //given
        when(accountDao.exists(anyLong())).thenReturn(true);
        doNothing().when(this.accountDao).transferMoney(anyLong(), anyLong(), any(BigDecimal.class));

        //when
        this.accountFacade.transferMoney(1, 2, new BigDecimal(100));

        // then
        verify(this.accountDao).transferMoney(anyLong(), anyLong(), any(BigDecimal.class));

    }


    @Test(expected = DataIntegrityException.class)
    public void should_throw_exception_while_receiver_not_found_in_transfer_money() {

        //given
        when(accountDao.exists(eq(1L))).thenReturn(true);
        when(accountDao.exists(eq(2L))).thenReturn(false);

        //when
        this.accountFacade.transferMoney(1L, 2, new BigDecimal(100));

        // then

    }

    @Test
    public void should_add_money_to_account() {

        //given
        when(accountDao.exists(eq(1L))).thenReturn(true);
        when(accountDao.addMoney(anyLong(), any(BigDecimal.class))).thenReturn(new Account());

        //when
        Account acc = accountFacade.addMoney(1L, new BigDecimal(20));

        //then
        verify(accountDao).addMoney(anyLong(), any(BigDecimal.class));
    }

    @Test(expected = DataIntegrityException.class)
    public void should_throw_exception_while_account_not_found_on_adding_money() {

        //given
        when(accountDao.exists(eq(1L))).thenReturn(false);

        //when
        this.accountFacade.transferMoney(1L, 2, new BigDecimal(100));

        // then

    }

}
