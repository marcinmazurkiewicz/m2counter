package dev.mazurkiewicz.auth;

import dev.mazurkiewicz.user.UserResponse;

import javax.validation.Valid;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest loginRequest) {
        return authorizationService.login(loginRequest);
    }

    @POST
    @Path("/refresh")
    public Response refresh(@CookieParam("refresh_token") String refreshToken) {
        return authorizationService.refreshToken(refreshToken);
    }

    @POST
    @Path("/register")
    public UserResponse register(@Valid RegisterRequest registerRequest) {
        return authorizationService.register(registerRequest);
    }
}
