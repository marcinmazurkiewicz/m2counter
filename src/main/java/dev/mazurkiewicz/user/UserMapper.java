package dev.mazurkiewicz.user;

import dev.mazurkiewicz.auth.RegisterRequest;
import dev.mazurkiewicz.auth.role.Role;
import dev.mazurkiewicz.util.PasswordUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserMapper {

    public User mapRequestToEntity(RegisterRequest request) {
        User result = new User();
        result.setEmail(request.getEmail().toLowerCase());
        result.setPassword(PasswordUtils.hashPassword(request.getPassword()));
        result.setNick(request.getNick());
        return result;
    }

    public UserResponse mapEntityToResponse(User entity) {
        Set<String> roles = entity.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
        return new UserResponse(entity.getUid(), entity.getEmail(), entity.getNick(), roles);
    }
}
