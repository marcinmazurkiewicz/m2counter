package dev.mazurkiewicz.character;

import dev.mazurkiewicz.exception.ResourceNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    public CharacterService(CharacterRepository repository, CharacterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CharacterResponse getPlayerById(Long playerId) {
        return repository.getCharacterById(playerId)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Player with id %d not found", playerId)));
    }

    public CharacterResponse savePlayer(CharacterRequest characterRequest, UUID uid) {
        Character character = mapper.mapRequestToEntity(characterRequest);
        character.setUserId(uid);
        repository.saveCharacter(character);
        return mapper.mapEntityToResponse(character);
    }

    public List<CharacterResponse> getCharactersForUser(UUID uid) {
        List<Character> charactersForUser = repository.getCharactersForUser(uid);
        return charactersForUser.stream()
                .map(mapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public void createDefaultCharacter(String name, UUID userId) {
        Character character = new Character();
        character.setUserId(userId);
        character.setName(name);
        repository.saveCharacter(character);
    }
}
