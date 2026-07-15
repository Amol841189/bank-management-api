package com.dnyaneshwar.bank.controller;

import com.dnyaneshwar.bank.dto.DepositRequest;
import com.dnyaneshwar.bank.dto.DepositResponse;
import com.dnyaneshwar.bank.dto.TransactionResponse;
import com.dnyaneshwar.bank.dto.WithdrawRequest;
import com.dnyaneshwar.bank.dto.WithdrawResponse;

import com.dnyaneshwar.bank.service.AccountService;
import com.dnyaneshwar.bank.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService,
                            TransactionService transactionService ) {
        this.accountService = accountService;
        this.transactionService = transactionService;
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

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory( 
                            @PathVariable String accountNumber) {

        List<TransactionResponse> response =
                transactionService.getTransactionHistory(accountNumber);

        return ResponseEntity.ok(response);
    }

    /**
     * Withdraw money from an account.
     *
     * Endpoint:
     * POST /api/accounts/withdraw
     *
     * Example Request:
     * {
     *   "accountNumber": "1000000012",
     *   "amount": 5000
     * }
     */
    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawResponse> withdraw( 
                    @RequestBody WithdrawRequest request) {

        WithdrawResponse response = accountService.withdraw(request);

        return ResponseEntity.ok(response);
    }

}