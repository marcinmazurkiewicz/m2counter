package dev.mazurkiewicz.player;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerMapper {
    public Player mapRequestToEntity(PlayerRequest request) {
        Player result = new Player();
        result.setName(request.getName());
        return result;
    }

    public PlayerResponse mapEntityToResponse(Player entity) {
        PlayerResponse result = new PlayerResponse();
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }
}
