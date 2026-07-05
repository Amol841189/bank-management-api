package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.entity.Account;
import com.dnyaneshwar.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public double getBalance(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new RuntimeException("Account Not Found")
                );

        return account.getBalance();
    }
}