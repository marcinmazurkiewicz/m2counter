package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.user.User;
import dev.mazurkiewicz.user.UserMapper;
import dev.mazurkiewicz.user.UserResponse;
import dev.mazurkiewicz.user.UserService;
import dev.mazurkiewicz.util.PasswordUtils;
import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AuthorizationService {
    private final UserService userService;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    public AuthorizationService(UserService userService, TokenService tokenService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public Response login(LoginRequest loginRequest) {
        User user = userService.findUser(loginRequest.getUsername());
        if (PasswordUtils.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.prepareTokens(userMapper.mapEntityToResponse(user));
        } else {
            throw new UnauthorizedException("Incorrect password");
        }
    }

    public Response refreshToken(String token) {
        RefreshToken refreshToken = tokenService.getValidRefreshToken(token);
        UserResponse user = userService.findUserById(refreshToken.getUserId());
        Response response = tokenService.prepareTokens(user);
        tokenService.removeToken(refreshToken);
        return response;
    }
}
