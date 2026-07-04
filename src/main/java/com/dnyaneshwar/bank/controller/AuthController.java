package com.dnyaneshwar.bank.controller;

import com.dnyaneshwar.bank.dto.RegisterRequest;
import com.dnyaneshwar.bank.dto.LoginRequest;
import com.dnyaneshwar.bank.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(
            UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return userService.login(request);
    }
}