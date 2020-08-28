/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author claes
 */
public class EntityTested {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        //Customer cus = new Customer("Hansen", "Lykkevej 1", new Date());
        Customer cus2 = new Customer("Olsen", "Skovvej 2",new Date());
        em.getTransaction().begin();
        //em.persist(cus);
        em.persist(cus2);
        em.getTransaction().commit();
        
        
    }
    
}
