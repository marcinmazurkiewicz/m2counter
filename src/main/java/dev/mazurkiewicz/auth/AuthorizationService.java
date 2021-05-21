package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.auth.role.Role;
import dev.mazurkiewicz.auth.role.RoleKind;
import dev.mazurkiewicz.auth.role.RoleRepository;
import dev.mazurkiewicz.auth.token.RefreshToken;
import dev.mazurkiewicz.auth.token.TokenService;
import dev.mazurkiewicz.character.CharacterService;
import dev.mazurkiewicz.user.User;
import dev.mazurkiewicz.user.UserMapper;
import dev.mazurkiewicz.user.UserResponse;
import dev.mazurkiewicz.user.UserService;
import dev.mazurkiewicz.util.PasswordUtils;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Set;

@ApplicationScoped
public class AuthorizationService {
    private final UserService userService;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CharacterService characterService;

    public AuthorizationService(UserService userService, TokenService tokenService,
                                UserMapper userMapper, RoleRepository roleRepository, CharacterService characterService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.characterService = characterService;
    }

    public Response login(LoginRequest loginRequest) {
        User user = userService.findUser(loginRequest.getEmail());
        if (PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.prepareTokens(userMapper.mapEntityToResponse(user));
        } else {
            throw new UnauthorizedException("Incorrect password");
        }
    }

    public Response refreshToken(String token) {
        RefreshToken refreshToken = tokenService.getValidRefreshToken(token);
        UserResponse user = userService.findByUserId(refreshToken.getUid());
        Response response = tokenService.prepareTokens(user);
        tokenService.removeToken(refreshToken);
        return response;
    }

    public UserResponse register(RegisterRequest registerRequest) {
        User user = userMapper.mapRequestToEntity(registerRequest);
        Role userRole = roleRepository.findRole(RoleKind.USER.name())
                .orElseGet(() -> roleRepository.createNewRole(RoleKind.USER.name()));
        user.setRoles(Set.of(userRole));
        UserResponse userResponse = userService.saveUser(user);
        characterService.createDefaultCharacter(userResponse.getNick(), userResponse.getUid());
        return userResponse;
    }
}
