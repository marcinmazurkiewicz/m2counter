package dev.mazurkiewicz.user;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class RoleRepository {
    private final EntityManager entityManager;

    public RoleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Role> findRole(String roleName) {
        return entityManager
                .createNamedQuery("Roles.findByName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public Role createNewRole(String roleName) {
        Role role = new Role();
        role.setRole(roleName);
        saveRole(role);
        return role;
    }

    @Transactional
    private void saveRole(Role role) {
        entityManager.persist(role);
    }
}
