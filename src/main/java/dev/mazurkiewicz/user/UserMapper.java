package dev.mazurkiewicz.user;

import dev.mazurkiewicz.EntityMapper;
import dev.mazurkiewicz.util.PasswordUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserMapper implements EntityMapper<User, UserRequest, UserResponse> {

    @Override
    public User mapRequestToEntity(UserRequest request) {
        User result = new User();
        result.setEmail(request.getEmail());
        result.setPassword(PasswordUtils.hashPassword(request.getPassword()));
        return result;
    }

    @Override
    public UserResponse mapEntityToResponse(User entity) {
        UserResponse result = new UserResponse();
        result.setId(entity.getId());
        result.setEmail(entity.getEmail());
        if (entity.getRoles() != null)
            result.setRoles(entity.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
        return result;
    }
}
