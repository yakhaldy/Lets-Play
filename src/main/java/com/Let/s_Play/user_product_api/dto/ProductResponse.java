package com.Let.s_Play.user_product_api.dto;

public record ProductResponse(
    String id,
    String name,
    String description,
    double price
) {

}
