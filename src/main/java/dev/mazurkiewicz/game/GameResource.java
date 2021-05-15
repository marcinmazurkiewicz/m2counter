package dev.mazurkiewicz.game;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/games")
public class GameResource {
    private final GameService service;

    public GameResource(GameService service) {
        this.service = service;
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
        return service.saveGame(gameRequest);
    }
}
