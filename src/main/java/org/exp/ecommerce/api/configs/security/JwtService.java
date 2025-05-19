package org.exp.ecommerce.api.configs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.exp.ecommerce.api.models.user.User;
import org.exp.ecommerce.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    
    private final UserRepository userRepository;
    
    public String generateToken(String email) {
        User user = userRepository.findByEmail(email);
        return "Bearer " + Jwts.builder()
                .subject(email)
                .claim("user", user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public SecretKey getSecretKey () {
        return Keys.hmacShaKeyFor("pK8s2V9zLq1xMn4TgR5cHb6YeWaQ0jDo".getBytes());
    }

    public User getUserObject(String token) {
        String email = extractEmail(token);
        return userRepository.findByEmail(email);
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token.replace("Bearer ", ""))
                .getPayload();
        return claims.getSubject();
    }
}
