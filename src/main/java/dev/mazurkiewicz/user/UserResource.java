package dev.mazurkiewicz.user;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/users")
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GET
    @Path("/{userId}")
    public UserResponse getUSer(@PathParam Long userId) {
        return service.findUserById(userId);
    }
}
