/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author claes
 */
public class EmployeeFacade {
    
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
        emf = _emf;
        instance = new EmployeeFacade();
            
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static Employee getEmployeeByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Query q2 = em.createQuery("Select e FROM Employee e WHERE e.id =:id");
            q2.setParameter("id", id);
            Employee em2 = (Employee)q2.getSingleResult();
            return em2;
        }finally {
            em.close();
        }
    }
    
    public static List<Employee> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try{
                TypedQuery<Employee> query = 
                        em.createQuery("SELECT e FROM Employee e where e.name=:name", Employee.class);
                        query.setParameter("name",name);
        return query.getResultList();
            
        }
             finally {
                em.close();
            }
    }
    
    public Employee createEmployee(String name, String address, Double salary){
        Employee emp = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        }finally {
            em.close();
        }
    }
    
    public static List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query = 
                       em.createQuery("Select employee from Employee employee",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    public static List<Employee> getEmpHiSalary(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Employee> query =
                    em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)",Employee.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    
}
