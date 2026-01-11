package com.example.banking.domain.model;

public record AccountNumber(String value) {

    public AccountNumber {
        if (!value.matches("\\d{13}")) {
            throw new IllegalArgumentException("Account number must be 13 digits");
        }
    }

}
