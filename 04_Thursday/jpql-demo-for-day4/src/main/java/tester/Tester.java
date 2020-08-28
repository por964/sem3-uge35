package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Tester {
    

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.persist(new Employee("chl888", "Claes", "Lindholm", new BigDecimal(888888)));
            em.getTransaction().commit();


            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            Query query = em.createQuery("Select e FROM Employee e WHERE e.salary > 100000");
            List<Employee> result = query.getResultList();
            System.out.println(result);

            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            String id = "klo999";
            Query q2 = em.createQuery("Select e FROM Employee e WHERE e.id =:id");
            q2.setParameter("id", id);
            Employee em2 = (Employee)q2.getSingleResult();
            System.out.println(em2.getFirstName());
            
            //3) Create a query to fetch the highest salary and print the value
            Query q3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            BigDecimal em3 = (BigDecimal)q3.getSingleResult();
            System.out.println(em3);
            
            //4) Create a query to fetch the firstName of all Employees and print the names
            Query q4 = em.createQuery("SELECT e.firstName FROM Employee e");
            List <String> res4 = q4.getResultList();
            System.out.println(res4);
            
            //5 Create a query to calculate the number of employees and print the number
            Query q5 = em.createQuery("SELECT COUNT(e) FROM Employee e");
            Long res5 = (Long)q5.getSingleResult();
            System.out.println(res5);
            
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query q6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            Employee em6 = (Employee)q6.getSingleResult();
            System.out.println(em6);
            
        } finally {
            em.close();
            emf.close();
        }
    }


}
