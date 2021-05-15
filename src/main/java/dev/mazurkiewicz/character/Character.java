package dev.mazurkiewicz.character;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "characters")
@NamedQuery(name = "Characters.findAllForUser",
        query = "SELECT c FROM characters c WHERE c.uid = :uid")
public class Character {
    private Long id;
    private String name;
    private String description;
    private UUID uid;

    @Id
    @SequenceGenerator(name = "characterSeq", sequenceName = "character_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "characterSeq")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }
}
