package dev.mazurkiewicz.game;

import dev.mazurkiewicz.character.Character;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "games")
@NamedQuery(name = "Games.findAll",
        query = "SELECT g FROM games g ORDER BY g.id")
public class Game {

    private UUID id;
    private String name;
    private Character creator;
    private Character gameMaster;
    private Set<Character> players;
    private boolean trustMode;

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    public Character getCreator() {
        return creator;
    }

    public void setCreator(Character creator) {
        this.creator = creator;
    }

    @ManyToOne
    @JoinColumn(name = "gm_id", nullable = false)
    public Character getGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(Character gameMaster) {
        this.gameMaster = gameMaster;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_character",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id", referencedColumnName = "id")})
    public Set<Character> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Character> players) {
        this.players = players;
    }

    public boolean isTrustMode() {
        return trustMode;
    }

    public void setTrustMode(boolean trustMode) {
        this.trustMode = trustMode;
    }

    @Transient
    public void addPlayer(Character player) {
        if (players == null) {
            players = new HashSet<>();
        }
        players.add(player);
    }
}
