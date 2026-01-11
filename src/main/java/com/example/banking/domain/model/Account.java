package com.example.banking.domain.model;

import java.math.BigDecimal;

public class Account {

    private final AccountNumber accountNumber;
    private Balance balance;

    public Account(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = new Balance(BigDecimal.ZERO);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.withdraw(amount);
    }

    public AccountNumber accountNumber() {
        return accountNumber;
    }

    public Balance balance() {
        return balance;
    }

}
