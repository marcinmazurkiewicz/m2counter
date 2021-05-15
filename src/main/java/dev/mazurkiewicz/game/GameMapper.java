package dev.mazurkiewicz.game;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameMapper {
    public Game mapRequestToEntity(GameRequest request) {
        Game toSave = new Game();
        toSave.setName(request.getName());
        return toSave;
    }

    public GameResponse mapEntityToResponse(Game entity) {
        GameResponse response = new GameResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}
