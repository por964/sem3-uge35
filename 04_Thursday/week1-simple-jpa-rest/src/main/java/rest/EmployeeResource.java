package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    private EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getEmployeeId(@PathParam("id") int id) {
     
        return Response.ok().entity(gson.toJson(facade.getEmployeeById(id))).build();
     
    }
    
    @GET
    @Path("emp_hi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpHiSalary(){
         
        return Response.ok().entity(gson.toJson(facade.getEmpHiSalary())).build();
    }
    
    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name) {
         
        return Response.ok().entity(gson.toJson(facade.getEmployeeByName(name))).build();
    }
    
    

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
