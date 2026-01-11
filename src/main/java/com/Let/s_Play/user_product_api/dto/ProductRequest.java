package com.Let.s_Play.user_product_api.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;



public record ProductRequest(
    @NotBlank(message = "Name is required")
    String name,
    
    String description,
    
    @Min(value = 0, message = "Price must be positive")
    double price
){}