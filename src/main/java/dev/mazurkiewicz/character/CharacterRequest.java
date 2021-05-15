package dev.mazurkiewicz.character;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CharacterRequest {
    @NotBlank
    private final String name;
    private final String description;

    @JsonCreator
    public CharacterRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
