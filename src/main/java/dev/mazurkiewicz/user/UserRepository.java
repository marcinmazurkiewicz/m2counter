package dev.mazurkiewicz.user;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

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

    public Optional<User> findUser(String username) {
        return entityManager.createNamedQuery("Users.findByEmail", User.class)
                .setParameter("email", username)
                .getResultList().stream().findFirst();
    }
}
