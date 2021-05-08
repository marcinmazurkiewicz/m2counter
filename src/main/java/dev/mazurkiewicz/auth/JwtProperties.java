package dev.mazurkiewicz.auth;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneId;

@ApplicationScoped
public class JwtProperties {
    @ConfigProperty(name = "jwt.token-prefix")
    String tokenPrefix;
    @ConfigProperty(name = "jwt.token-expiration")
    Integer tokenExpirationAfterSeconds;
    @ConfigProperty(name = "jwt.issuer")
    String issuer;

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public Integer getTokenExpirationAfterSeconds() {
        return tokenExpirationAfterSeconds;
    }

    public String getIssuer() {
        return issuer;
    }

    public ZoneId getTimezoneId() {
        return ZoneId.of("Europe/Warsaw");
    }
}
