package com.example.banking.application.usecase;


import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;
import com.example.banking.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class WithdrawMoneyUseCase {

    private final AccountRepository repository;

    public WithdrawMoneyUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(String accNo, BigDecimal amount) {
        Account account = repository.findByAccountNumber(new AccountNumber(accNo)).orElseThrow();
        account.withdraw(amount);
        repository.save(account);
    }

}
