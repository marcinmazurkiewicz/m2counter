package dev.mazurkiewicz.player;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/players")
public class PlayerResource {
    private final PlayerService service;

    public PlayerResource(PlayerService service) {
        this.service = service;
    }

    @GET
    @Path("/{playerId}")
    public PlayerResponse getAllGames(@PathParam Long playerId) {
        return service.getPlayerById(playerId);
    }

    @POST
    public PlayerResponse savePlayer(PlayerRequest playerRequest) {
        return service.savePlayer(playerRequest);
    }
}
