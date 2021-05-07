package dev.mazurkiewicz.user;

import dev.mazurkiewicz.exception.ResourceNotFoundException;
import dev.mazurkiewicz.util.PasswordUtils;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserMapper mapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        User user = mapper.mapRequestToEntity(userRequest);
        Role userRole = roleRepository.findRole(RoleKind.USER.name())
                .orElseGet(() -> roleRepository.createNewRole(RoleKind.USER.name()));
        user.setRoles(Set.of(userRole));
        userRepository.saveUser(user);
        return mapper.mapEntityToResponse(user);
    }

    public UserResponse login(UserRequest userRequest) {
        User user = userRepository.findUser(userRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", userRequest.getEmail())));
        if (PasswordUtils.verifyPassword(userRequest.getPassword(), user.getPassword())) {
            return mapper.mapEntityToResponse(user);
        } else {
            throw new UnauthorizedException();
        }
    }
}
