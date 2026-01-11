package com.example.banking.application.usecase;


import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountNumber;
import com.example.banking.domain.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateAccountUseCase {

    private final AccountRepository repository;

    public CreateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public void execute(String accountNumber) {
        AccountNumber accNo = new AccountNumber(accountNumber);
        repository.save(new Account(accNo));
    }

}
