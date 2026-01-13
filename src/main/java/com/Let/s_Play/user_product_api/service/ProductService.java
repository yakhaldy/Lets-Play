package com.Let.s_Play.user_product_api.service;


import com.Let.s_Play.user_product_api.dto.ProductRequest;
import com.Let.s_Play.user_product_api.dto.ProductResponse;
import com.Let.s_Play.user_product_api.model.Product;
import com.Let.s_Play.user_product_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import com.Let.s_Play.user_product_api.exception.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private String currentUserId() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private boolean isAdmin() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

   
    public ProductResponse create(ProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .userId(currentUserId())
                .build();

        productRepository.save(product);

        return map(product);
    }

    public List<ProductResponse> getAll() {
        if (isAdmin()) {
            return productRepository.findAll().stream()
                    .map(this::map)
                    .toList();
        }

        return productRepository.findByUserId(currentUserId())
                .stream()
                .map(this::map)
                .toList();
    }

    
    public void delete(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (!isAdmin() && !product.getUserId().equals(currentUserId())) {
            throw new ForbiddenException("Forbidden");
        }

        productRepository.deleteById(id);
    }

    private ProductResponse map(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getUserId()
        );
    }
}