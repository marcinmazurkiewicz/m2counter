package dev.mazurkiewicz.user;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository {
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public Optional<User> findUserByEmail(String username) {
        return entityManager.createNamedQuery("Users.findByEmail", User.class)
                .setParameter("email", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    public Optional<User> findByUserId(UUID id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
