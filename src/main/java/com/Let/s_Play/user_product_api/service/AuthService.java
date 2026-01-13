package com.Let.s_Play.user_product_api.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Let.s_Play.user_product_api.model.User;
import com.Let.s_Play.user_product_api.dto.AuthResponse;
import com.Let.s_Play.user_product_api.dto.LoginRequest;
import com.Let.s_Play.user_product_api.dto.UserRequest;
import com.Let.s_Play.user_product_api.dto.UserResponse;
import com.Let.s_Play.user_product_api.repository.UserRepository;
import com.Let.s_Play.user_product_api.security.JwtUtil;
import com.Let.s_Play.user_product_api.exception.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponse register(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .role("USER")
                .build();

        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public AuthResponse login(LoginRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.email());
        if (optionalUser.isEmpty()) {
            throw new UnauthorizedException("Invalid email or password");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(userRequest.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getRole());
        return new AuthResponse(token);
    }

}
