package dbfacade;

import entity.Customer;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author claes
 */
public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        
        Customer cust1 = facade.addCustomer("Claes", "Lindholm");
        Customer cust2 = facade.addCustomer("Poul", "Thomsen");
        Customer cust3 = facade.addCustomer("Ole", "Olsen");
        Customer cust4 = facade.addCustomer("Per", "Sand");
        Customer cust5 = facade.addCustomer("Poul", "Lindholm");
        
        System.out.println("Kunde 1: "+facade.findByID(1));
        System.out.println("Kunde 2: "+facade.findByID(2));
        System.out.println("Kunde 3: "+facade.findByID(3));
        System.out.println("Kunde 4: "+facade.findByID(4)+"\n");
        
        System.out.println("Number of customers: "+facade.allCustomers().size()+"\n");
        
        findByLastName("Lindholm").stream().forEach(System.out::println);
        
        System.out.println("Efternavn: "+facade.findByLastName("Sand"));
        
    }

    private static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    
    public Customer addCustomer(String firstName, String lastName) {
        Customer cust = new Customer(firstName, lastName, new Date());
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally {
            em.close();
        }
    }
    
    public Customer findByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer cus = em.find(Customer.class,id);
            return cus;
        }finally {
            em.close();
        }
    }
    
        public List<Customer> allCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer from Customer customer",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
        
        public static List<Customer> findByLastName(String name) {
            EntityManager em = emf.createEntityManager();
            try{
                TypedQuery<Customer> query = 
                        em.createQuery("SELECT c FROM Customer c where c.lastName=:name", Customer.class);
                        query.setParameter("name",name);
        return query.getResultList();
            
        }
             finally {
                em.close();
            }
        }
    
}
