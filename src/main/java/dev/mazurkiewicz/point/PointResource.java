package dev.mazurkiewicz.point;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/points")
public class PointResource {
    private final PointService service;

    public PointResource(PointService service) {
        this.service = service;
    }

    @GET
    public List<Point> getPoints() {
        return service.getPoints();
    }

    @POST
    public PointResponse scorePoint(PointRequest point) {
        return service.savePoint(point);
    }

}
