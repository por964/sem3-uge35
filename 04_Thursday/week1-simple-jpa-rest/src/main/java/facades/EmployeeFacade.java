/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
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
    
    private EmployeeFacade() {
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            EmployeeDTO e1 = new EmployeeDTO(employee);
            return e1;
        } finally {
            em.close();
        }
    } 
   
    
    public EmployeeDTO getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try{
                Query qe = 
                        em.createQuery("SELECT e FROM Employee e where e.name=:name");
                        qe.setParameter("name",name);
                        Employee e2 = (Employee) qe.getSingleResult();
                        EmployeeDTO em2 = new EmployeeDTO(e2);
        return em2;
            
        }
             finally {
                em.close();
            }
    }
    
    public Employee createEmployee(String name, String address, Double salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = getEntityManager();
        List<EmployeeDTO> emlist = new ArrayList();
        try {
            TypedQuery<Employee> query = 
                       em.createQuery("Select employee from Employee employee",Employee.class);
            List<Employee> em3 = query.getResultList();
            em3.stream().forEach(p -> {
            emlist.add(new EmployeeDTO(p));
        });
            return emlist;
    } finally {
            em.close();
        }
    
    }
        
    
    
    public EmployeeDTO getEmpHiSalary(){
         EntityManager em = emf.createEntityManager();
        try{
            Query q =
                    em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)",Employee.class);
            Employee res4 = (Employee) q.getSingleResult();
            EmployeeDTO em4 = new EmployeeDTO(res4);
            return em4;
        }finally {
            em.close();
        }
    }
    
    
}
