package dev.mazurkiewicz.point;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PointRepository {
    private final EntityManager entityManager;

    public PointRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void savePoint(Point point) {
        entityManager.persist(point);
    }

    public List<Point> getPoints() {
        return entityManager.createNamedQuery("Points.findAll", Point.class).getResultList();
    }

}
