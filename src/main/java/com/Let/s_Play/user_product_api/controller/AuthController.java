package com.Let.s_Play.user_product_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Let.s_Play.user_product_api.dto.AuthResponse;
import com.Let.s_Play.user_product_api.dto.LoginRequest;
import com.Let.s_Play.user_product_api.dto.UserRequest;
import com.Let.s_Play.user_product_api.dto.UserResponse;
import com.Let.s_Play.user_product_api.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = authService.register(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest userRequest) {
        AuthResponse authResponse = authService.login(userRequest);
        return ResponseEntity.ok(authResponse);
    }
}