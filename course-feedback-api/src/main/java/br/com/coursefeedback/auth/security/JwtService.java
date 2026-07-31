package br.com.coursefeedback.auth.security;

import br.com.coursefeedback.auth.domain.User;
import br.com.coursefeedback.auth.domain.UserRole;
import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class JwtService {

    private final JwtUtil jwtUtil;

    @Inject
    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());

        return jwtUtil.generateToken(user.getUserId(), claims);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validate(token);
    }

    public String extractUserId(String token) {
        return jwtUtil.extractClaims(token).getSubject();
    }

    public String extractEmail(String token) {
        Claims claims = jwtUtil.extractClaims(token);
        return claims.get("email", String.class);
    }

    public UserRole extractRole(String token) {
        Claims claims = jwtUtil.extractClaims(token);
        return UserRole.valueOf(claims.get("role", String.class));
    }
}