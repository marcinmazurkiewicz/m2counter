package dev.mazurkiewicz.player;

import dev.mazurkiewicz.exception.ResourceNotFoundException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    public PlayerService(PlayerRepository repository, PlayerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PlayerResponse getPlayerById(Long playerId) {
        return repository.getPlayerById(playerId)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Player with id %d not found", playerId)));
    }

    public PlayerResponse savePlayer(PlayerRequest playerRequest) {
        Player player = mapper.mapRequestToEntity(playerRequest);
        repository.savePlayer(player);
        return mapper.mapEntityToResponse(player);
    }
}
