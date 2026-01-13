package com.Let.s_Play.user_product_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

// import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

     private final SecretKey key;  
    private final long expiration;

    public JwtUtil(JwtProperties properties) {
        this.key = Keys.hmacShaKeyFor(properties.secret().getBytes());
        this.expiration = properties.expiration();
    }


    public String generateToken(String userId, String role) {
    return Jwts.builder()
            .subject(userId)
            .claim("role", role)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key, Jwts.SIG.HS256)
            .compact();
}


    public Jws<Claims> validateToken(String token) {
    return Jwts.parser()
            .verifyWith((SecretKey) key)
            .build()
            .parseSignedClaims(token);
}
}
