package dev.mazurkiewicz.user;

import java.util.Set;
import java.util.UUID;

public class UserResponse {
    private final UUID uid;
    private final String email;
    private final Set<String> roles;

    public UserResponse(UUID uid, String email, Set<String> roles) {
        this.uid = uid;
        this.email = email;
        this.roles = roles;
    }

    public UUID getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
