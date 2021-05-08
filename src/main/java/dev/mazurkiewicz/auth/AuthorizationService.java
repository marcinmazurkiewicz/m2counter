package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.exception.ResourceNotFoundException;
import dev.mazurkiewicz.user.*;
import dev.mazurkiewicz.util.PasswordUtils;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthorizationService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthorizationService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findUser(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", loginRequest.getUsername())));
        if (PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user);
        } else {
            throw new UnauthorizedException();
        }
    }


}
