package dev.mazurkiewicz.player;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class PlayerRepository {
    private EntityManager entityManager;

    public PlayerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Player> getPlayerById(Long playerId) {
        Player player = entityManager.find(Player.class, playerId);
        return Optional.ofNullable(player);
    }

    @Transactional
    public Player savePlayer(Player player) {
        entityManager.persist(player);
        return player;
    }
}
