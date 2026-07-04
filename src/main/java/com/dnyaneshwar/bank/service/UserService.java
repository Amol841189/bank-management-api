package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.RegisterRequest;
import com.dnyaneshwar.bank.entity.User;
import com.dnyaneshwar.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String register(RegisterRequest request) {

        if (userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            return "Email already exists";
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        // For now, store plain text.
        // Later we will use BCrypt.
        user.setPassword(request.getPassword());

        user.setRole("CUSTOMER");

        userRepository.save(user);

        return "Registration successful";
    }
}