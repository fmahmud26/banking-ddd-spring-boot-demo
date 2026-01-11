package com.example.banking.application.usecase;


import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;
import com.example.banking.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class DepositMoneyUseCase {

    private final AccountRepository repository;

    public DepositMoneyUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(String accNo, BigDecimal amount) {
        Account account = repository.findByAccountNumber(new AccountNumber(accNo)).orElseThrow();
        account.deposit(amount);
        repository.save(account);
    }

}
