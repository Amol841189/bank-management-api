package com.dnyaneshwar.bank.controller;

import com.dnyaneshwar.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}/balance")
    public double getBalance(
            @PathVariable String accountNumber) {

        return accountService.getBalance(accountNumber);
    }
}