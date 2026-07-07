package com.dnyaneshwar.bank.controller;

import com.dnyaneshwar.bank.dto.DepositRequest;
import com.dnyaneshwar.bank.dto.DepositResponse;
import com.dnyaneshwar.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}/balance")
    public BigDecimal getBalance(
            @PathVariable String accountNumber) {

        return accountService.getBalance(accountNumber);
    }

    @PostMapping("/deposit")
    public DepositResponse deposit(
            @RequestBody DepositRequest request) {
        
        return accountService.deposit(request);
    }
}