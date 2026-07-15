package com.dnyaneshwar.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawResponse {

    private String message;
    private String accountNumber;
    private BigDecimal withdrawnAmount;
    private BigDecimal currentBalance;
    private LocalDateTime transactionDateTime;

    public WithdrawResponse() {
    }

    public WithdrawResponse(String message,
                            String accountNumber,
                            BigDecimal withdrawnAmount,
                            BigDecimal currentBalance,
                            LocalDateTime transactionDateTime) {
        this.message = message;
        this.accountNumber = accountNumber;
        this.withdrawnAmount = withdrawnAmount;
        this.currentBalance = currentBalance;
        this.transactionDateTime = transactionDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getWithdrawnAmount() {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(BigDecimal withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}