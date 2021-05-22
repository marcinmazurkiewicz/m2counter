package dev.mazurkiewicz.game;

import org.eclipse.microprofile.jwt.Claim;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.UUID;

@Path("/games")
@RequestScoped
public class GameResource {
    private final GameService service;
    private final UUID userId;

    public GameResource(GameService service, @Claim("uid") String uid) {
        this.service = service;
        this.userId = UUID.fromString(uid);
    }

    @GET
    public List<GameResponse> getAllGames() {
        return service.getAllGames();
    }

    @GET
    @Path("/{gameId}")
    public GameResponse getAllGames(@PathParam Long gameId) {
        return service.getGameById(gameId);
    }

    @POST
    public GameResponse saveGame(@Valid GameRequest gameRequest) {
        return service.createGame(gameRequest, userId);
    }
}
