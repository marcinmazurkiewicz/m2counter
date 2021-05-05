package dev.mazurkiewicz.point;

import dev.mazurkiewicz.EntityMapper;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;

@ApplicationScoped
public class PointMapper implements EntityMapper<Point, PointRequest, PointResponse> {

    @Override
    public Point mapRequestToEntity(PointRequest request) {
        Point result = new Point();
        result.setGameId(request.getGameId());
        result.setPlayerId(request.getPlayerId());
        result.setUpdatingPlayerId(request.getUpdatingPlayerId());
        result.setPoints(request.getPoints());
        result.setTimestamp(Instant.now());
        return result;
    }

    @Override
    public PointResponse mapEntityToResponse(Point entity) {
        PointResponse result = new PointResponse();
        result.setId(entity.getId());
        result.setPoints(entity.getPoints());
        result.setGameId(entity.getGameId());
        result.setPlayerId(entity.getPlayerId());
        result.setUpdatingPlayerId(entity.getUpdatingPlayerId());
        result.setTimestamp(entity.getTimestamp());
        return result;
    }
}
