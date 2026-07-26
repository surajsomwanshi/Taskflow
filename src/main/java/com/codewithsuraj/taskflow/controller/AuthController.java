package com.codewithsuraj.taskflow.controller;

import com.codewithsuraj.taskflow.dto.LoginRequest;
import com.codewithsuraj.taskflow.dto.RegisterRequest;
import com.codewithsuraj.taskflow.dto.TokenResponse;
import com.codewithsuraj.taskflow.security.JwtService;
import com.codewithsuraj.taskflow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, AuthService authService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request){
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        String role = auth.getAuthorities().iterator().next()
                .getAuthority().replace("ROLE", "");
        String token = jwtService.generateToken(auth.getName(), role);
        return new TokenResponse(token);
    }
}
