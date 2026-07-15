package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.LoginRequest;
import com.dnyaneshwar.bank.dto.LoginResponse;
import com.dnyaneshwar.bank.dto.RegisterRequest;
import com.dnyaneshwar.bank.dto.RegisterResponse;
import com.dnyaneshwar.bank.dto.AccountResponse;
import com.dnyaneshwar.bank.entity.Account;
import com.dnyaneshwar.bank.entity.User;
import com.dnyaneshwar.bank.service.AccountNumberGenerator;
import com.dnyaneshwar.bank.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    public RegisterResponse register(RegisterRequest request) {

        // Check Existing User
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new RegisterResponse(
                "Email already exits",
                false,
                null
            );
        }

        // Create User
        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("CUSTOMER");

        userRepository.save(user);

        // Create Account
        Account account = new Account();

        account.setAccountNumber(accountNumberGenerator.generate());
        account.setAccountType("SAVINGS");
        account.setBalance(BigDecimal.ZERO);
        account.setCreatedAt(LocalDateTime.now());

        // Link User and Account
        user.setAccount(account);

        // Save User (Account is saved automatically because of CascadeType.ALL)
        userRepository.save(user);
        //accountRepository.save(account);

        
        AccountResponse accountResponse = new AccountResponse(
                user.getFullName(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getCreatedAt()
        );

        return new RegisterResponse(
                "Account Created Successfully",
                true,
                accountResponse
        );

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