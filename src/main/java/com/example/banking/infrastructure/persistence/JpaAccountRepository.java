package com.example.banking.infrastructure.persistence;


import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;
import com.example.banking.domain.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final SpringDataAccountRepository repository;

    public JpaAccountRepository(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> findByAccountNumber(AccountNumber accountNumber) {
        return repository.findById(accountNumber.value())
                .map(e -> {
                    Account account = new Account(new AccountNumber(e.getAccountNumber()));
                    account.deposit(e.getBalance());
                    return account;
                });
    }

    @Override
    public void save(Account account) {
        repository.save(new AccountEntity(
                account.accountNumber().value(),
                account.balance().value()
        ));
    }

}
