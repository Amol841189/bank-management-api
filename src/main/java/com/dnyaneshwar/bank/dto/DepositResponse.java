package com.dnyaneshwar.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositResponse {

    private String message;
    private String accountNumber;
    private BigDecimal depositedAmount;
    private BigDecimal currentBalance;
    private LocalDateTime transactionDateTime;

    public DepositResponse() {
    }

    public DepositResponse(String message,
                           String accountNumber,
                           BigDecimal depositedAmount,
                           BigDecimal currentBalance,
                           LocalDateTime transactionDateTime) {

        this.message = message;
        this.accountNumber = accountNumber;
        this.depositedAmount = depositedAmount;
        this.currentBalance = currentBalance;
        this.transactionDateTime = transactionDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getDepositedAmount() {
        return depositedAmount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}