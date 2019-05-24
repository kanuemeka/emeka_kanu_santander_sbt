package com.kanue.interviews.santander.accountrestapi.resource;

import com.kanue.interviews.santander.accountrestapi.model.Account;
import com.kanue.interviews.santander.accountrestapi.service.AccountNotFoundException;
import com.kanue.interviews.santander.accountrestapi.service.AccountService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

public class AccountResourceTest {

    protected AccountResource accountResource;

    protected AccountService mockService;

    @Before
    public void setup(){
        mockService = mock(AccountService.class);

        accountResource = new AccountResource(mockService);
    }

    @Test
    public void shouldPersistAccountAndReturnPositiveResponse(){
        Account account = new Account("John Account", "12-01-20");
        Long returnedId = 1L;

        when(mockService.saveAccount(account)).thenReturn(returnedId);

        Response response = accountResource.saveAccount(account);

        assertNotNull(response);
        assertEquals(201, response.getStatus());

        verify(mockService, times(1)).saveAccount(account);
    }

    @Test
    public void shouldReturnAccountForSearch()throws Exception{
        Long testId = 1L;
        Account account = new Account(testId, "John Account", "12-01-20");

        when(mockService.getAccount(testId)).thenReturn(account);

        Response response = accountResource.getAccount(testId);

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertNotNull(response.getEntity());

        Account returnedAccount = (Account) response.getEntity();
        assertEquals(account.getName(), returnedAccount.getName());
        assertEquals(account.getSortCode(), returnedAccount.getSortCode());

        verify(mockService, times(1)).getAccount(testId);
    }

    @Test
    public void shouldFailAccountSearchAndReturn404ForSearch()throws Exception{
        Long testId = 1L;

        when(mockService.getAccount(testId)).thenThrow(new AccountNotFoundException());

        Response response = accountResource.getAccount(testId);

        assertNotNull(response);
        assertEquals(404, response.getStatus());


        verify(mockService, times(1)).getAccount(testId);
    }

    @Test
    public void shouldFailAccountUpdateAccountNotExist()throws Exception{
        Long testId = 1L;
        Account account = new Account(testId, "John Account", "12-01-20");

        doThrow(new AccountNotFoundException()).when(mockService).updateAccount(testId, account);

        Response response = accountResource.updateAccount(testId, account);

        assertNotNull(response);
        assertEquals(404, response.getStatus());

        verify(mockService, times(1)).updateAccount(testId, account);
    }

    @Test
    public void shouldUpdateAccount()throws Exception{
        Long testId = 1L;
        Account account = new Account(testId, "John Account", "12-01-20");

        when(mockService.getAccount(testId)).thenThrow(new AccountNotFoundException());

        Response response = accountResource.updateAccount(testId, account);

        assertNotNull(response);
        assertEquals(200, response.getStatus());

        verify(mockService, times(1)).updateAccount(testId, account);
    }
}
