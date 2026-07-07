package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.DepositRequest;
import com.dnyaneshwar.bank.dto.DepositResponse;
import com.dnyaneshwar.bank.entity.Account;
import com.dnyaneshwar.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BigDecimal getBalance(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new RuntimeException("Account Not Found")
                );

        return account.getBalance();
    }

    @Transactional
    public DepositResponse deposit(DepositRequest request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        account.setBalance(
                account.getBalance().add(request.getAmount())
        );

        accountRepository.save(account);

        return new DepositResponse(
                "Deposit Successful",
                account.getAccountNumber(),
                request.getAmount(),
                account.getBalance(),
                LocalDateTime.now()
        );
    }

}