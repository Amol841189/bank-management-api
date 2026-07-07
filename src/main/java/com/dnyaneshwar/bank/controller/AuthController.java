package com.dnyaneshwar.bank.controller;

import com.dnyaneshwar.bank.dto.RegisterRequest;
import com.dnyaneshwar.bank.dto.LoginRequest;
import com.dnyaneshwar.bank.dto.LoginResponse;
import com.dnyaneshwar.bank.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LoginResponse> login(
                    @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        if (response.getToken() == null) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}