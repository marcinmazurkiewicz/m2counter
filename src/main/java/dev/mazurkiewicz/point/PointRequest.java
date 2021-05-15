package dev.mazurkiewicz.point;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotEmpty;

public class PointRequest {
    @NotEmpty
    private final int points;
    @NotEmpty
    private final long playerId;
    @NotEmpty
    private final long gameId;
    @NotEmpty
    private final long updatingPlayerId;

    @JsonCreator
    public PointRequest(int points, long playerId, long gameId, long updatingPlayerId) {
        this.points = points;
        this.playerId = playerId;
        this.gameId = gameId;
        this.updatingPlayerId = updatingPlayerId;
    }

    public int getPoints() {
        return points;
    }

    public long getPlayerId() {
        return playerId;
    }

    public long getGameId() {
        return gameId;
    }

    public long getUpdatingPlayerId() {
        return updatingPlayerId;
    }
}
