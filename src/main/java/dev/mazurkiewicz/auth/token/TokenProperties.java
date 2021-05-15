package dev.mazurkiewicz.auth.token;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneId;

@ApplicationScoped
public class TokenProperties {
    @ConfigProperty(name = "token.jwt.prefix")
    String tokenPrefix;
    @ConfigProperty(name = "token.jwt.expiration")
    Integer tokenExpirationAfterSeconds;
    @ConfigProperty(name = "token.jwt.issuer")
    String issuer;
    @ConfigProperty(name = "token.refresh.name")
    String refreshTokenName;
    @ConfigProperty(name = "token.refresh.expiration")
    int refreshTokenExpirationAfterSeconds;

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public Integer getTokenExpirationAfterSeconds() {
        return tokenExpirationAfterSeconds;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getRefreshTokenName() {
        return refreshTokenName;
    }

    public int getRefreshTokenExpirationAfterSeconds() {
        return refreshTokenExpirationAfterSeconds;
    }

    public ZoneId getTimezoneId() {
        return ZoneId.of("Europe/Warsaw");
    }
}
