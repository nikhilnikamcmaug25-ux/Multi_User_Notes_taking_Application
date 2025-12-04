package com.notes.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.notes.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String jwtSecret = "ReplaceThisWithASecureLongSecretForProduction_ChangeNow!";
    private final long jwtExpirationMs = 1000 * 60 * 60 * 24; // 24h

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(jwtSecret.getBytes()));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole() != null ? user.getRole().name() : "USER");
        claims.put("userId", user.getId());
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        Object r = claims.get("role");
        return r != null ? r.toString() : null;
    }

    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
        Object uid = claims.get("userId");
        if (uid == null) return null;
        if (uid instanceof Integer) return ((Integer) uid).longValue();
        if (uid instanceof Long) return (Long) uid;
        if (uid instanceof String) return Long.parseLong((String) uid);
        return null;
    }
}
