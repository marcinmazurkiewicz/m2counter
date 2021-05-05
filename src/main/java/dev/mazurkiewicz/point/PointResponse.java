package dev.mazurkiewicz.point;

import java.time.Instant;

public class PointResponse {
    private Long id;
    private Integer points;
    private Long playerId;
    private Long gameId;
    private Long updatingPlayerId;
    private Instant timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUpdatingPlayerId() {
        return updatingPlayerId;
    }

    public void setUpdatingPlayerId(Long updatingPlayerId) {
        this.updatingPlayerId = updatingPlayerId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
