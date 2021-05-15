package dev.mazurkiewicz.user;

import dev.mazurkiewicz.exception.ResourceNotFoundException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    private final UserMapper mapper;
    private final UserRepository userRepository;

    public UserService(UserMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public UserResponse saveUser(User user) {
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
