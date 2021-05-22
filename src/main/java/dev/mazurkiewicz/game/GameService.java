package dev.mazurkiewicz.game;

import dev.mazurkiewicz.character.Character;
import dev.mazurkiewicz.character.CharacterRepository;
import dev.mazurkiewicz.exception.ResourceNotFoundException;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameService {
    private final GameRepository repository;
    private final GameMapper mapper;
    private final CharacterRepository characterRepository;

    public GameService(GameRepository repository, GameMapper mapper, CharacterRepository characterRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.characterRepository = characterRepository;
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

    public GameResponse createGame(GameRequest gameRequest, UUID userId) {
        Game game = mapper.mapRequestToEntity(gameRequest);
        Character creator = characterRepository.findCharacterById(gameRequest.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Character with %s not found", gameRequest.getCreatorId())));
        if (!creator.getUserId().equals(userId)) {
            throw new UnauthorizedException("This character don't belong to you!");
        }
        game.setCreator(creator);
        game.setGameMaster(creator);
        game.addPlayer(creator);
        repository.saveGame(game);
        return mapper.mapEntityToResponse(game);
    }
}
