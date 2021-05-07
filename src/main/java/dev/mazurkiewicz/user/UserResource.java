package dev.mazurkiewicz.user;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/users")
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @POST
    public UserResponse registerUser(UserRequest userRequest) {
        return service.registerUser(userRequest);
    }

    @POST
    @Path("login")
    public UserResponse login(UserRequest userRequest) {
        return service.login(userRequest);
    }
}
