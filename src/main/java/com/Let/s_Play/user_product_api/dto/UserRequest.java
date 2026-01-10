package com.Let.s_Play.user_product_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank
    String name,
    
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password
) {}
