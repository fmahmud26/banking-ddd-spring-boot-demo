package com.example.banking.domain.repository;

import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findByAccountNumber(AccountNumber accountNumber);

    void save(Account account);
}
