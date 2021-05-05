package dev.mazurkiewicz.point;

public class PointRequest {
    private int points;
    private long playerId;
    private long gameId;
    private long updatingPlayerId;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getUpdatingPlayerId() {
        return updatingPlayerId;
    }

    public void setUpdatingPlayerId(long updatingPlayerId) {
        this.updatingPlayerId = updatingPlayerId;
    }
}
