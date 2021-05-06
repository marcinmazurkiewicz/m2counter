package dev.mazurkiewicz.game;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GameRepository {
    private final EntityManager em;

    public GameRepository(EntityManager em) {
        this.em = em;
    }

    public List<Game> getAllGames() {
        return em.createNamedQuery("Games.findAll", Game.class).getResultList();
    }

    public Optional<Game> getGameById(Long gameId) {
        Game game = em.find(Game.class, gameId);
        return Optional.ofNullable(game);
    }

    @Transactional
    public void saveGame(Game game) {
        em.persist(game);
    }
}
