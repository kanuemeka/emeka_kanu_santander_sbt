package com.kanue.interviews.santander.accountrestapi.service;

import com.kanue.interviews.santander.accountrestapi.model.Account;
import com.kanue.interviews.santander.accountrestapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Long saveAccount(final Account account){
        Account persisted = accountRepository.save(account);

        return persisted.getId();
    }

    public void updateAccount(final Long id, final Account account) throws AccountNotFoundException, InvalidRequestException {

        if(!id.equals(account.getId()))
            throw new InvalidRequestException();

        Optional<Account> optional = accountRepository.findById(account.getId());

        if(optional.isPresent()){
            accountRepository.save(account);
        }
        else
            throw new AccountNotFoundException();

    }

    public Account getAccount(final Long id)throws AccountNotFoundException{

        Optional<Account> optional = accountRepository.findById(id);

        if(optional.isPresent())
            return optional.get();
        else
            throw new AccountNotFoundException();
    }

    public void deleteAccount(final Long id){

        Optional<Account> optional = accountRepository.findById(id);

        if(optional.isPresent()){
            accountRepository.delete(optional.get());
        }

    }
}
