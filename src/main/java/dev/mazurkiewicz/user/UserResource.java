package dev.mazurkiewicz.user;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/users")
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GET
    @Path("/{userId}")
    @PermitAll
    public UserResponse getUser(@PathParam UUID userId) {
        return service.findByUserId(userId);
    }
}
