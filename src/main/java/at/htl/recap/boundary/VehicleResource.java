package at.htl.recap.boundary;

import at.htl.recap.control.VehicleRepository;
import at.htl.recap.entity.Vehicle;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("vehicle")
public class VehicleResource {

    @Inject
    VehicleRepository vehicleRepository;

    @Path("path/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return String.format("hello %s", name);
    }

    @Path("query")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloQuery(
            @QueryParam("grade") int grade,
            @QueryParam("name") String name) {
        return String.format("hello %d%s", grade , name);
    }

    @Path("object")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Vehicle getDummyVehicle(){
        return new Vehicle("Opel", "Kadett", 1973);
    }

    @Path("response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDummyVehicleResponse(){
        Vehicle opel = new Vehicle("Opel", "Kadett", 1973);
        return Response.ok(opel).build();
    }

    @Path("db")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleFromDb(){
        Vehicle opel = new Vehicle("Opel", "Kadett", 1973);
        Vehicle kadett = vehicleRepository.save(opel);
        return Response.ok(kadett).build();
    }


}
