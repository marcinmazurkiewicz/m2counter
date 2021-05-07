package dev.mazurkiewicz.user;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class UserRepository {
    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findUser(String username) {
        return entityManager.createNamedQuery("Users.findByEmail", User.class)
                .setParameter("email", username)
                .getResultList().stream().findFirst();
    }

    @Transactional
    public void saveUser(User user) {
        Role role = findRole("USER");
        user.setRoles(Set.of(role));
        entityManager.persist(user);
    }

    @Transactional
    private void saveRole(Role role) {
        entityManager.persist(role);
    }

    private Role findRole(String roleName) {
        return entityManager
                .createNamedQuery("Roles.findByName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList()
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setRole(roleName);
                    saveRole(role);
                    return role;
                });

    }
}
