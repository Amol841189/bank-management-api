package com.dnyaneshwar.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Long transactionId;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private String description;
    private LocalDateTime transactionDate;

    public TransactionResponse() {
    }

    public TransactionResponse(Long transactionId,
                               String type,
                               BigDecimal amount,
                               BigDecimal balanceAfterTransaction,
                               String description,
                               LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}