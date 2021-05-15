package dev.mazurkiewicz.character;

import java.util.UUID;

public class CharacterResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final UUID uid;

    public CharacterResponse(Long id, String name, String description, UUID uid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.uid = uid;
    }

    public Long getId() {
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
