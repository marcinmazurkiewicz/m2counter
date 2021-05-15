package dev.mazurkiewicz.game;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class GameRequest {
    @NotBlank
    private final String name;

    @JsonCreator
    public GameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
