package com.example.banking.infrastructure.persistence;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class AccountEntity {

    @Id
    private String accountNumber;
    private BigDecimal balance;

    protected AccountEntity() {
    }

    public AccountEntity(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
