package com.Let.s_Play.user_product_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.Let.s_Play.user_product_api.security.JwtProperties;

@EnableConfigurationProperties(JwtProperties.class) // khasni n9lab 3liha 
@SpringBootApplication
public class UserProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProductApiApplication.class, args);
	}

}
