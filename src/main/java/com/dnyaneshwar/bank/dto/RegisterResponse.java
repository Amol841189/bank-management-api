package com.dnyaneshwar.bank.dto;

public class RegisterResponse {

    private String message;
    private boolean success;
    private AccountResponse account;

    public RegisterResponse(
        String message,
        boolean success,
            AccountResponse account) {

        this.message = message;
        this.success = success;
        this.account = account;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public AccountResponse getAccount() {
        return account;
    }
}