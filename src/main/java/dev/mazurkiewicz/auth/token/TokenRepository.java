package dev.mazurkiewicz.auth.token;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class TokenRepository {
    private final EntityManager entityManager;

    public TokenRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<RefreshToken> getRefreshToken(String token) {
        return entityManager.createNamedQuery("RefreshTokens.findByToken", RefreshToken.class)
                .setParameter("token", token).getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public void saveRefreshToken(RefreshToken refreshToken) {
        entityManager.persist(refreshToken);
    }

    @Transactional
    public void removeToken(RefreshToken token) {
        entityManager.createNamedQuery("RefreshTokens.removeToken")
                .setParameter("id", token.getId())
                .executeUpdate();
    }
}
