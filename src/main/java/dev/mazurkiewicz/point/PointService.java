package dev.mazurkiewicz.point;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PointService {
    private final PointRepository repository;
    private final PointMapper mapper;

    public PointService(PointRepository repository, PointMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Point> getPoints() {
        return repository.getPoints();
    }

    public PointResponse savePoint(PointRequest pointRequest) {
        Point point = mapper.mapRequestToEntity(pointRequest);
        repository.savePoint(point);
        return mapper.mapEntityToResponse(point);
    }
}
