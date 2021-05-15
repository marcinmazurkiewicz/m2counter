package dev.mazurkiewicz.auth.role;

import io.quarkus.security.jpa.RolesValue;

import javax.persistence.*;

@Entity(name = "roles")
@NamedQueries({
        @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM roles r WHERE r.role = :roleName")
})
public class Role {
    private Long id;
    @RolesValue
    private String role;

    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "roleSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String authority) {
        this.role = authority;
    }

    @Override
    public String toString() {
        return role;
    }
}