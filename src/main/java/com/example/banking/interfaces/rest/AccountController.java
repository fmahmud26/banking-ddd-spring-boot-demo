package com.example.banking.interfaces.rest;


import com.example.banking.application.usecase.CreateAccountUseCase;
import com.example.banking.application.usecase.DepositMoneyUseCase;
import com.example.banking.application.usecase.SendMoneyUseCase;
import com.example.banking.application.usecase.WithdrawMoneyUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccount;
    private final DepositMoneyUseCase depositMoney;
    private final WithdrawMoneyUseCase withdrawMoney;
    private final SendMoneyUseCase sendMoney;


    public AccountController(CreateAccountUseCase createAccount, DepositMoneyUseCase depositMoney, WithdrawMoneyUseCase withdrawMoney, SendMoneyUseCase sendMoney) {
        this.createAccount = createAccount;
        this.depositMoney = depositMoney;
        this.withdrawMoney = withdrawMoney;
        this.sendMoney = sendMoney;
    }

    @PostMapping
    public void createAccount(@RequestParam String accountNumber) {
        createAccount.execute(accountNumber);
    }

    @PostMapping("/{accNo}/deposit")
    public void deposit(@PathVariable String accNo, @RequestParam BigDecimal amount) {
        depositMoney.execute(accNo, amount);
    }

    @PostMapping("/{accNo}/withdraw")
    public void withdraw(@PathVariable String accNo, @RequestParam BigDecimal amount) {
        withdrawMoney.execute(accNo, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam BigDecimal amount) {
        sendMoney.execute(fromAccount, toAccount, amount);
    }

}
