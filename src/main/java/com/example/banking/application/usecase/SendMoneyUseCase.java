package com.example.banking.application.usecase;


import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;
import com.example.banking.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class SendMoneyUseCase {

    private final AccountRepository repository;

    public SendMoneyUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(String fromAccount, String toAccount, BigDecimal amount) {
        Account sender = repository.findByAccountNumber(new AccountNumber(fromAccount)).orElseThrow();
        Account receiver = repository.findByAccountNumber(new AccountNumber(toAccount)).orElseThrow();

        sender.withdraw(amount);
        receiver.deposit(amount);

        repository.save(sender);
        repository.save(receiver);
    }

}
