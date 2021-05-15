package dev.mazurkiewicz.player;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PlayerRequest {
    @NotBlank
    private final String name;

    @JsonCreator
    public PlayerRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
