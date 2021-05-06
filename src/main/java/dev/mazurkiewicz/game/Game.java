package dev.mazurkiewicz.game;

import javax.persistence.*;

@Entity(name = "games")
@NamedQuery(name = "Games.findAll",
        query = "SELECT g FROM games g ORDER BY g.id")
public class Game {

    private Long id;
    private String name;

    @Id
    @SequenceGenerator(name = "gameSeq", sequenceName = "game_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "gameSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
