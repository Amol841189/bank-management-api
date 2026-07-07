package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class AccountNumberGenerator {

    private final AccountRepository accountRepository;

    public AccountNumberGenerator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generate() {
        long nextNumber = accountRepository.count() + 1000000001L;
        return String.valueOf(nextNumber);
    }
}