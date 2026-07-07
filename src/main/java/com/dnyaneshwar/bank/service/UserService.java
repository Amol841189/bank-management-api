package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.LoginRequest;
import com.dnyaneshwar.bank.dto.LoginResponse;
import com.dnyaneshwar.bank.dto.RegisterRequest;
import com.dnyaneshwar.bank.entity.Account;
import com.dnyaneshwar.bank.entity.User;
import com.dnyaneshwar.bank.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountNumberGenerator accountNumberGenerator;

    public UserService(UserRepository userRepository,
                       AccountNumberGenerator accountNumberGenerator) {

        this.userRepository = userRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Transactional
    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        // Create User
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Later replace with BCrypt
        user.setRole("CUSTOMER");

        // Create Account
        Account account = new Account();
        account.setAccountNumber(accountNumberGenerator.generate());
        account.setBalance(BigDecimal.ZERO);

        // Link User and Account
        user.setAccount(account);

        // Save User (Account is saved automatically because of CascadeType.ALL)
        userRepository.save(user);

        return "Registration successful";
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponse(null, "User not found");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse(null, "Invalid password");
        }

        return new LoginResponse("HDFCBANK-LOGIN-TOKEN-DNYANESHWAR00002345", "Login successful");

    }
}