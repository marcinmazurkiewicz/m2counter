package dev.mazurkiewicz.auth;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @POST
    public Response login(LoginRequest loginRequest) {
        String token = authorizationService.login(loginRequest);
        return Response.ok().header(HttpHeaders.AUTHORIZATION, token).build();
    }

}
