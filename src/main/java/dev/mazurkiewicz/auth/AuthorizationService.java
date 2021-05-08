package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.exception.ResourceNotFoundException;
import dev.mazurkiewicz.user.*;
import dev.mazurkiewicz.util.PasswordUtils;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthorizationService {
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public AuthorizationService(UserRepository userRepository, JwtProperties jwtProperties) {
        this.userRepository = userRepository;
        this.jwtProperties = jwtProperties;
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findUser(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", loginRequest.getUsername())));
        if (PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            return generateToken(user);
        } else {
            throw new UnauthorizedException();
        }
    }

    private String generateToken(User user) {
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
