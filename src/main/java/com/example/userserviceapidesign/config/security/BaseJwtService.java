package com.example.userserviceapidesign.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public final class BaseJwtService {

    private SecretKey key;
    @Value( "${spring.security.jwt-secret-key}")
    private String jwtSecretKey;


    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        key= Keys.hmacShaKeyFor(keyBytes);
    }
    public Jws<Claims> parse(String token) {
        return Jwts.parser().decryptWith( key).build().parseSignedClaims(token);

    }

}
