package com.Let.s_Play.user_product_api.dto;


import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
    @NotBlank
    String id,
    @NotBlank
    String name,
    String description,
    @NotBlank
    double price
) {
    
}
