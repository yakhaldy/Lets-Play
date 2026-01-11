package com.Let.s_Play.user_product_api.exception;

public record ErrorResponse(
        int status,
        String message
) {}
