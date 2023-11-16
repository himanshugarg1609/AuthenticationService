package com.him.authservice.controllers;

import com.him.authservice.DTOs.LoginRequestDTO;
import com.him.authservice.DTOs.UserDTO;
import com.him.authservice.Exceptions.InvalidCredentialsException;
import com.him.authservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signUp")



    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) throws InvalidCredentialsException {
        authService.signUp(userDTO.toUser());
        return ResponseEntity.ok("Signed up successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) throws InvalidCredentialsException {
        return ResponseEntity.ok(authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String token) {
        if(token == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        return ResponseEntity.ok(authService.validate(token));
    }
}
