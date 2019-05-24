package com.kanue.interviews.santander.accountrestapi.resource;

import com.kanue.interviews.santander.accountrestapi.model.Account;
import com.kanue.interviews.santander.accountrestapi.service.AccountNotFoundException;
import com.kanue.interviews.santander.accountrestapi.service.AccountService;
import com.kanue.interviews.santander.accountrestapi.service.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;

@Path("/accounts")
@Component
public class AccountResource {

    private static Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    private AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAccount(Account account){
        LOG.info("Received request to save account ");

        Long id = accountService.saveAccount(account);

        LOG.info("Account Created. Returning Response");

        return Response.created(URI.create("/accounts/"+id)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("id") Long accountId){
        Account account;
        try {
            account = accountService.getAccount(accountId);
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(account).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("id") Long accountId, Account account){

        try {
            accountService.updateAccount(accountId, account);
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (InvalidRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") Long accountId){
        accountService.deleteAccount(accountId);

        return Response.ok().build();
    }
}
