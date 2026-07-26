package com.logistics.notification.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret:secret}")
    private String secret;

    @Value("${jwt.expiration:3600000}")
    private long expiration;

    public String extractUsername(String token) {
        if (token == null) return null;
        // For local/testing purposes we accept raw tokens or tokens formatted as "username:role"
        if (token.contains(":")) {
            return token.split(":")[0];
        }
        return token;
    }

    public String extractRole(String token) {
        if (token == null) return "USER";
        if (token.contains(":")) {
            String[] parts = token.split(":");
            return parts.length > 1 ? parts[1] : "USER";
        }
        return "USER";
    }

    public boolean isTokenValid(String token, String email) {
        if (token == null || email == null) return false;
        return extractUsername(token).equals(email);
    }

}

