package dev.mazurkiewicz.game;

import dev.mazurkiewicz.EntityMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameMapper implements EntityMapper<Game, GameRequest, GameResponse> {
    @Override
    public Game mapRequestToEntity(GameRequest request) {
        Game toSave = new Game();
        toSave.setName(request.getName());
        return toSave;
    }

    @Override
    public GameResponse mapEntityToResponse(Game entity) {
        GameResponse response = new GameResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}
