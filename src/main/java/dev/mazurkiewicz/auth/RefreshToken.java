package dev.mazurkiewicz.auth;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refresh_tokens")
@NamedQueries({
        @NamedQuery(name = "RefreshTokens.findByToken", query = "SELECT rt FROM refresh_tokens rt WHERE rt.refreshToken = :token"),
        @NamedQuery(name = "RefreshTokens.removeToken", query = "DELETE FROM refresh_tokens rt WHERE rt.id = :id")
})
public class RefreshToken {
    private Long id;
    private Long userId;
    private String refreshToken;
    private Instant createdAt;
    private Instant expiredAt;

    @Id
    @SequenceGenerator(name = "refreshTokenSeq", sequenceName = "refresh_token_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "refreshTokenSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "expired_at")
    public Instant getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Instant expiredAt) {
        this.expiredAt = expiredAt;
    }
}
