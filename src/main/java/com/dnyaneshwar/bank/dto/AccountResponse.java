package com.dnyaneshwar.bank.dto;

import java.time.LocalDateTime;

public class AccountResponse {

    private String holderName;
    private String accountNumber;
    private String accountType;
    private LocalDateTime createdAt;

    public AccountResponse(
            String holderName,
            String accountNumber,
            String accountType,
            LocalDateTime createdAt) {

        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.createdAt = createdAt;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}