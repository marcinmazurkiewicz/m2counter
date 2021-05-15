package dev.mazurkiewicz.user;

import dev.mazurkiewicz.auth.RegisterRequest;
import dev.mazurkiewicz.auth.Role;
import dev.mazurkiewicz.util.PasswordUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserMapper {

    public User mapRequestToEntity(RegisterRequest request) {
        User result = new User();
        result.setEmail(request.getEmail().toLowerCase());
        result.setPassword(PasswordUtils.hashPassword(request.getPassword()));
        return result;
    }

    public UserResponse mapEntityToResponse(User entity) {
        UserResponse result = new UserResponse();
        result.setId(entity.getId());
        result.setEmail(entity.getEmail());
        if (entity.getRoles() != null)
            result.setRoles(entity.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
        return result;
    }
}
