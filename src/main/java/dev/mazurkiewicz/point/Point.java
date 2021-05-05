package dev.mazurkiewicz.point;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "points")
public class Point {
    private Long id;
    private Integer points;
    private Long playerId;
    private Long gameId;
    private Long updatingPlayerId;
    private Instant timestamp;

    @Id
    @SequenceGenerator(name = "pointSeq", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "pointSeq")
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

    public void setPlayerId(Long player) {
        this.playerId = player;
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
