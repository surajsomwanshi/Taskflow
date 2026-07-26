package com.codewithsuraj.taskflow.service;

import com.codewithsuraj.taskflow.dto.RegisterRequest;
import com.codewithsuraj.taskflow.entity.User;
import com.codewithsuraj.taskflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();

        user.setUsername(request.username());

        user.setPasswordHash(
                passwordEncoder.encode(request.password()));

        // Default role if none supplied
        user.setRole(
                request.role() == null || request.role().isBlank()
                        ? "USER"
                        : request.role().toUpperCase());

        userRepository.save(user);
    }
}