package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.user.Role;
import dev.mazurkiewicz.user.User;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ApplicationScoped
public class TokenService {
    private final JwtProperties jwtProperties;

    public TokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(User user) {
        String token = Jwt.issuer(jwtProperties.getIssuer())
                .upn(user.getEmail())
                .groups(user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()))
                .claim("uid", user.getId())
                .issuedAt(Instant.now())
                .expiresAt(LocalDateTime.now()
                        .plusSeconds(jwtProperties.getTokenExpirationAfterSeconds())
                        .atZone(jwtProperties.getTimezoneId()).toInstant())
                .sign();
        return String.format("%s %s", jwtProperties.getTokenPrefix(), token);
    }
}
