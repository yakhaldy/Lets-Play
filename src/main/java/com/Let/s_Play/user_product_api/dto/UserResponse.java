package com.Let.s_Play.user_product_api.dto;

public record UserResponse(
    String id,
    String name,
    String email,
    String role
) {}