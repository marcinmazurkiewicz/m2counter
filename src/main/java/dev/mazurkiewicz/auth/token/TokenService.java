package dev.mazurkiewicz.auth.token;

import dev.mazurkiewicz.user.UserResponse;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class TokenService {
    private final TokenProperties tokenProperties;
    private final TokenRepository tokenRepository;

    public TokenService(TokenProperties tokenProperties, TokenRepository tokenRepository) {
        this.tokenProperties = tokenProperties;
        this.tokenRepository = tokenRepository;
    }

    public Response prepareTokens(UserResponse user) {
        NewCookie refreshCookie = new NewCookie(tokenProperties.getRefreshTokenName(), createRefreshToken(user.getUid()),
                "/", null, null, tokenProperties.getRefreshTokenExpirationAfterSeconds(),
                false, true);
        return Response.ok()
                .header(HttpHeaders.AUTHORIZATION, generateJwtToken(user))
                .cookie(refreshCookie)
                .entity(user)
                .build();
    }

    public RefreshToken getValidRefreshToken(String token) {
        if (token == null)
            throw new UnauthorizedException("Token is null");
        RefreshToken refreshToken = tokenRepository.getRefreshToken(token)
                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));
        if (isTokenExpired(refreshToken))
            throw new UnauthorizedException("Token has expired");
        return refreshToken;
    }

    public void removeToken(RefreshToken token) {
        tokenRepository.removeToken(token);
    }

    private String generateJwtToken(UserResponse user) {
        String token = Jwt.issuer(tokenProperties.getIssuer())
                .upn(user.getEmail())
                .groups(user.getRoles())
                .claim("uid", user.getUid())
                .issuedAt(Instant.now())
                .expiresAt(LocalDateTime.now()
                        .plusSeconds(tokenProperties.getTokenExpirationAfterSeconds())
                        .atZone(tokenProperties.getTimezoneId()).toInstant())
                .sign();
        return String.format("%s %s", tokenProperties.getTokenPrefix(), token);
    }


    private String createRefreshToken(UUID uid) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUid(uid);
        refreshToken.setRefreshToken(generateRefreshToken());
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setExpiredAt(LocalDateTime.now()
                .plusSeconds(tokenProperties.getRefreshTokenExpirationAfterSeconds())
                .atZone(tokenProperties.getTimezoneId()).toInstant());
        tokenRepository.saveRefreshToken(refreshToken);
        return refreshToken.getRefreshToken();
    }

    private String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    private boolean isTokenExpired(RefreshToken token) {
        boolean isExpired = Instant.now().isAfter(token.getExpiredAt());
        if (isExpired)
            tokenRepository.removeToken(token);
        return isExpired;
    }
}
