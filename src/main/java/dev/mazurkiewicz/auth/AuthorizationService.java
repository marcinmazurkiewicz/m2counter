package dev.mazurkiewicz.auth;

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

    public AuthorizationService(UserService userService, TokenService tokenService,
                                UserMapper userMapper, RoleRepository roleRepository) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public Response login(LoginRequest loginRequest) {
        User user = userService.findUser(loginRequest.getEmail());
        if (PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.prepareTokens(user);
        } else {
            throw new UnauthorizedException("Incorrect password");
        }
    }

    public Response refreshToken(String token) {
        RefreshToken refreshToken = tokenService.getValidRefreshToken(token);
        User user = userService.findById(refreshToken.getUserId());
        Response response = tokenService.prepareTokens(user);
        tokenService.removeToken(refreshToken);
        return response;
    }

    public UserResponse register(RegisterRequest registerRequest) {
        User user = userMapper.mapRequestToEntity(registerRequest);
        Role userRole = roleRepository.findRole(RoleKind.USER.name())
                .orElseGet(() -> roleRepository.createNewRole(RoleKind.USER.name()));
        user.setRoles(Set.of(userRole));
        return userService.saveUser(user);
    }
}
