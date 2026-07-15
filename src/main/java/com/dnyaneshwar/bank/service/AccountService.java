package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.DepositRequest;
import com.dnyaneshwar.bank.dto.DepositResponse;
import com.dnyaneshwar.bank.dto.WithdrawRequest;
import com.dnyaneshwar.bank.dto.WithdrawResponse;

import com.dnyaneshwar.bank.entity.Account;
import com.dnyaneshwar.bank.entity.Transaction;
import com.dnyaneshwar.bank.repository.AccountRepository;
import com.dnyaneshwar.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.dnyaneshwar.bank.enums.TransactionStatus;
import com.dnyaneshwar.bank.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    LocalDateTime transactionTime = LocalDateTime.now();

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
                            
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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

        // Step 1: Find the account using the account number.
        // If the account doesn't exist, throw an exception.

        // PessimisticLock
        Account account = accountRepository
                         .findByAccountNumberForUpdate(request.getAccountNumber())
                         .orElseThrow(() -> new RuntimeException("Account not found"));
                
        // Step 2: Validate the deposit amount.
        // Deposit amount must be greater than zero.
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        // Step 3: Add the deposit amount to the current account balance.
        account.setBalance(
                account.getBalance().add(request.getAmount())
        );

        // Step 4: Save the updated account balance in the database.
        accountRepository.save(account);

        // Step 5: Create a new transaction record for this deposit.
        Transaction transaction = new Transaction();

        // Generate a unique transaction ID.
        transaction.setTransactionId(UUID.randomUUID().toString());

        // Associate this transaction with the account.
        transaction.setAccount(account);

        // Set the transaction type.
        transaction.setType(TransactionType.DEPOSIT);

        // Store the deposited amount.
        transaction.setAmount(request.getAmount());

        // Store the account balance after the deposit.
        transaction.setBalanceAfterTransaction(account.getBalance());

        // Add a description for the transaction.
        transaction.setDescription("Cash Deposit");

        // Mark the transaction as successful.
        transaction.setStatus(TransactionStatus.SUCCESS);

        // Record the date and time of the transaction.
        transaction.setCreatedAt(transactionTime);

        // Step 6: Save the transaction in the database.
        transactionRepository.save(transaction);

        // Step 7: Return the response to the client.
        return new DepositResponse(
                "Deposit Successful",
                account.getAccountNumber(),
                request.getAmount(),
                account.getBalance(),
                transactionTime
        );
    }

    @Transactional
    public WithdrawResponse withdraw(WithdrawRequest request) {

        /*
        * Step 1:
        * Retrieve the account and lock the row.
        *
        * PESSIMISTIC_WRITE ensures that while this transaction
        * is updating the balance, no other transaction can
        * update the same account.
        */
        Account account = accountRepository
                .findByAccountNumberForUpdate(request.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        /*
        * Step 2:
        * Validate available balance.
        */
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
                throw new RuntimeException("Insufficient balance");
        }

        /*
        * Step 3:
        * Deduct the withdrawal amount.
        */
        account.setBalance(
                account.getBalance().subtract(request.getAmount())
        );

        /*
        * Step 4:
        * Save the updated account.
        */
        accountRepository.save(account);

        /*
        * Step 5:
        * Create a transaction history record.
        */
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(request.getAmount());
        transaction.setBalanceAfterTransaction(account.getBalance());
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setStatus(TransactionStatus.SUCCESS);
        transaction.setDescription("Cash Withdrawal");
        transaction.setCreatedAt(transactionTime);

        transactionRepository.save(transaction);

        /*
        * Step 6:
        * Return the response.
        */
        return new WithdrawResponse(
                "Withdrawal Successful",
                account.getAccountNumber(),
                request.getAmount(),
                account.getBalance(),
                transactionTime
        );
    }

}