package dev.mazurkiewicz.game;

import dev.mazurkiewicz.exception.ResourceNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameService {
    private final GameRepository repository;
    private final GameMapper mapper;

    public GameService(GameRepository repository, GameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<GameResponse> getAllGames() {
        List<Game> games = repository.getAllGames();
        return games
                .stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public GameResponse getGameById(Long gameId) {
        return repository.getGameById(gameId)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Game with id %d not found", gameId)));
    }

    public GameResponse saveGame(GameRequest gameRequest) {
        Game game = mapper.mapRequestToEntity(gameRequest);
        repository.saveGame(game);
        return mapper.mapEntityToResponse(game);
    }
}
