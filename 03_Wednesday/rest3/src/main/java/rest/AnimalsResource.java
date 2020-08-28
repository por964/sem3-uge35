package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import mode1.AnimalNoDB;

@Path("animals")
public class AnimalsResource {

    @Context
    private UriInfo context;
    
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of AnimalsResource
     */
    public AnimalsResource() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Vuf...";
    }

    @GET
    @Path("animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson2() {
        return "[Dog, Cat, Bird, Cow, Wolf]";
    }
    
    /*@GET
    @Path("dyr")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal() {
        AnimalNoDB ani = new AnimalNoDB("And", "raprap");
        String jsonString = GSON.toJson(ani);
        return jsonString;
    }
    */

    /**
     * PUT method for updating or creating an instance of AnimalsResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
