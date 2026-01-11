package com.example.banking.domain.model;

import java.math.BigDecimal;

public final class Balance {

    private final BigDecimal amount;

    public Balance(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Negative balance not allowed");
        this.amount = amount;
    }

    public Balance deposit(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Deposit must be positive");
        return new Balance(amount.add(money));
    }

    public Balance withdraw(BigDecimal money) {
        if (money.compareTo(amount) > 0)
            throw new IllegalArgumentException("Insufficient balance");
        return new Balance(amount.subtract(money));
    }

    public BigDecimal value() {
        return amount;
    }

}
