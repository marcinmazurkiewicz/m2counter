package dev.mazurkiewicz.character;

import java.util.UUID;

public class CharacterResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final UUID uid;

    public CharacterResponse(UUID id, String name, String description, UUID uid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uid = uid;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUid() {
        return uid;
    }
}
