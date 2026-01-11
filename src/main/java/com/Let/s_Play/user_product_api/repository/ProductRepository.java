package com.Let.s_Play.user_product_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Let.s_Play.user_product_api.model.Product;


public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByIdAndUserId(String id, String userId);

    List<Product> findByUserId(String id);
}

