package dev.mazurkiewicz.player;

import dev.mazurkiewicz.EntityMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerMapper implements EntityMapper<Player, PlayerRequest, PlayerResponse> {
    @Override
    public Player mapRequestToEntity(PlayerRequest request) {
        Player result = new Player();
        result.setName(request.getName());
        return result;
    }

    @Override
    public PlayerResponse mapEntityToResponse(Player entity) {
        PlayerResponse result = new PlayerResponse();
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }
}
