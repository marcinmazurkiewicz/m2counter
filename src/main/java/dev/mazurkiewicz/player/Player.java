package dev.mazurkiewicz.player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "players")
public class Player {
    private Long id;
    private String name;

    @Id
    @SequenceGenerator(name = "playerSeq", sequenceName = "player_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "playerSeq")
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
