package dev.mazurkiewicz.game;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class GameRequest {
    @NotBlank
    private final String name;
    @NotNull
    private final UUID creatorId;

    @JsonCreator
    public GameRequest(String name, UUID creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public UUID getCreatorId() {
        return creatorId;
    }
}
