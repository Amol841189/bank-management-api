package com.dnyaneshwar.bank.dto;

public class LoginRequest {

    private String email;
    private String password;

    public LoginRequest(){
    }


    // Getter and Setter Methods
    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

}
