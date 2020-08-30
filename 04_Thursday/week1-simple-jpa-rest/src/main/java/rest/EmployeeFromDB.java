/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import static rest.EmployeeResource.emf;

/**
 * REST Web Service
 *
 * @author claes
 */
@Path("employee")
public class EmployeeFromDB {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeFromDB
     */
    public EmployeeFromDB() {
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmps() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<Employee> emps = query.getResultList();
            return new Gson().toJson(emps);
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("emp_no/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployee(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
            query.setParameter("id", id);
            List<Employee> emps = query.getResultList();
            return new Gson().toJson(emps);
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("emp_hi")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpHiSalary(){
         EntityManager em = emf.createEntityManager();
        try{
            Query qh =
                    em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)",Employee.class);
            Employee emph = (Employee)qh.getSingleResult();
            return new Gson().toJson(emph);
        }finally {
            em.close();
        }
    }
    
    @GET
    @Path("emp_name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeesByName(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        try{
                TypedQuery<Employee> qname = 
                        em.createQuery("SELECT e FROM Employee e where e.name=:name", Employee.class);
                        qname.setParameter("name",name);
                        List<Employee> emps = qname.getResultList();
        return new Gson().toJson(emps);
            
        }
             finally {
                em.close();
            }
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeFromDB
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    

    /**
     * PUT method for updating or creating an instance of EmployeeFromDB
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
