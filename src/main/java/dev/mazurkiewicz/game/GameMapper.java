package dev.mazurkiewicz.game;

import dev.mazurkiewicz.character.CharacterResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameMapper {
    public Game mapRequestToEntity(GameRequest request) {
        Game toSave = new Game();
        toSave.setName(request.getName());
        return toSave;
    }

    public GameResponse mapEntityToResponse(Game entity) {
        CharacterResponse creator = new CharacterResponse(entity.getCreator().getId(), entity.getCreator().getName(), entity.getCreator().getDescription());
        CharacterResponse gameMaster = new CharacterResponse(entity.getCreator().getId(), entity.getCreator().getName(), entity.getCreator().getDescription());
        Set<CharacterResponse> players = entity.getPlayers()
                .stream()
                .map(x -> new CharacterResponse(x.getId(), x.getName(), x.getDescription()))
                .collect(Collectors.toSet());
        return new GameResponse(entity.getId(), entity.getName(), creator, gameMaster, players, entity.isTrustMode());

    }
}
