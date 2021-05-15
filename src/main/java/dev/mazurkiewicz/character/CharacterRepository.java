package dev.mazurkiewicz.character;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CharacterRepository {
    private EntityManager entityManager;

    public CharacterRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Character> getCharacterById(Long playerId) {
        Character character = entityManager.find(Character.class, playerId);
        return Optional.ofNullable(character);
    }

    @Transactional
    public Character saveCharacter(Character character) {
        entityManager.persist(character);
        return character;
    }

    public List<Character> getCharactersForUser(UUID uid) {
        return entityManager.createNamedQuery("Characters.findAllForUser", Character.class)
                .setParameter("uid", uid)
                .getResultList();
    }
}
