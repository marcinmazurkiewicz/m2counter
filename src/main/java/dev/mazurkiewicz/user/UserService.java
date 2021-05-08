package dev.mazurkiewicz.user;

import dev.mazurkiewicz.exception.ResourceNotFoundException;

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

    public User findUser(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with email %s not exist", email)));
    }

    public UserResponse findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .map(mapper::mapEntityToResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with id %d not exist", userId)));
    }
}
