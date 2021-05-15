package dev.mazurkiewicz.user;

import dev.mazurkiewicz.auth.Role;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@NamedQueries({
        @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM users u WHERE u.email = :email"),
        @NamedQuery(name = "Users.findByUid", query = "SELECT u FROM users u WHERE u.uid = :uid")
})
@UserDefinition
public class User {
    private Long id;
    @Username
    private String email;
    @Password
    private String password;
    @Roles
    private Set<Role> roles;
    private UUID uid;

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "userSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> authorities) {
        this.roles = authorities;
    }

    @Column(unique = true, nullable = false)
    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uuid) {
        this.uid = uuid;
    }
}
