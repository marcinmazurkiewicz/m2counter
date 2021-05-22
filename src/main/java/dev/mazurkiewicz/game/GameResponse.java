package dev.mazurkiewicz.game;

import dev.mazurkiewicz.character.CharacterResponse;

import java.util.Set;
import java.util.UUID;

public class GameResponse {
    private final UUID id;
    private final String name;
    private final CharacterResponse creator;
    private final CharacterResponse gameMaster;
    private final Set<CharacterResponse> players;
    private final boolean trustMode;

    public GameResponse(UUID id, String name, CharacterResponse creator, CharacterResponse gameMaster,
                        Set<CharacterResponse> players, boolean trustMode) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.gameMaster = gameMaster;
        this.players = players;
        this.trustMode = trustMode;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterResponse getCreator() {
        return creator;
    }

    public CharacterResponse getGameMaster() {
        return gameMaster;
    }

    public Set<CharacterResponse> getPlayers() {
        return players;
    }

    public boolean isTrustMode() {
        return trustMode;
    }
}
