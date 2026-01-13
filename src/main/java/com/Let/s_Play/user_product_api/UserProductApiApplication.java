package com.Let.s_Play.user_product_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Let.s_Play.user_product_api.model.User;
import com.Let.s_Play.user_product_api.repository.UserRepository;
import com.Let.s_Play.user_product_api.security.JwtProperties;

import lombok.RequiredArgsConstructor;

@EnableConfigurationProperties(JwtProperties.class) // khasni n9lab 3liha
@SpringBootApplication
public class UserProductApiApplication {

    @Value("${spring.data.mongodb.uri:NOT_SET}")
    private String mongoUri;

    public static void main(String[] args) {
        SpringApplication.run(UserProductApiApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    public class CreateAdmin implements CommandLineRunner {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Value("${admin.email:admin@admin.com}")
        private String adminEmail;

        @Value("${admin.username:admin}")
        private String adminUsername;

        @Value("${admin.password:admin123}")
        private String adminPassword;

        @Override
        public void run(String... args) throws Exception {
            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setName(adminUsername);
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);
            }

        }
    }
}
