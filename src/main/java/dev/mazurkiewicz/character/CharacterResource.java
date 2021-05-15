package dev.mazurkiewicz.character;

import org.eclipse.microprofile.jwt.Claim;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.UUID;

@Path("/characters")
@RolesAllowed("USER")
@RequestScoped
public class CharacterResource {
    private final CharacterService service;
    private final UUID uid;

    public CharacterResource(CharacterService service, @Claim("uid") String uid) {
        this.service = service;
        this.uid = UUID.fromString(uid);
    }

    @GET
    public List<CharacterResponse> getLoggedUserCharacters() {
        return service.getCharactersForUser(uid);
    }

    @GET
    @Path("/{playerId}/")
    public CharacterResponse getLoggedUserCharacter(@PathParam Long characterId) {
        System.out.println(uid);
        return service.getPlayerById(characterId);
    }

    @POST
    public CharacterResponse savePlayer(@Valid CharacterRequest characterRequest) {
        return service.savePlayer(characterRequest, uid);
    }
}
