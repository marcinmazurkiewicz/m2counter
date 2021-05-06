package dev.mazurkiewicz.game;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GameRepository {
    private final EntityManager em;

    public GameRepository(EntityManager em) {
        this.em = em;
    }

    public List<Game> getAllGames() {
        return em.createNamedQuery("Games.findAll", Game.class).getResultList();
    }

    @Transactional
    public void saveGame(Game game) {
        em.persist(game);
    }
}
