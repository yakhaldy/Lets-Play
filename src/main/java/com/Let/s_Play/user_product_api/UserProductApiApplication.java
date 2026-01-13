package com.Let.s_Play.user_product_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.Let.s_Play.user_product_api.security.JwtProperties;

@EnableConfigurationProperties(JwtProperties.class) // khasni n9lab 3liha 
@SpringBootApplication
public class UserProductApiApplication {
	
	@Value("${spring.data.mongodb.uri:NOT_SET}")
    private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(UserProductApiApplication.class, args);
	}


	 @Component
    public class MongoDebugRunner implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            System.out.println("MongoDB URI at startup: " + mongoUri);
        }
    }

}
