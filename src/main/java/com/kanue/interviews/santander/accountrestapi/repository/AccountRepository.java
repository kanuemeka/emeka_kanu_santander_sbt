package com.kanue.interviews.santander.accountrestapi.repository;

import com.kanue.interviews.santander.accountrestapi.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
