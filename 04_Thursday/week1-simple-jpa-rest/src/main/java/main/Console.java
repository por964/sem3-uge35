/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Employee;
import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author claes
 */
public class Console {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
        
        em.persist(new Employee("Claes","Kvaj 53", 12222.0));
        em.persist(new Employee("Niels","Avej 44", 13333.0));
        em.persist(new Employee("Hans","Svej 33", 14444.0));
        em.persist(new Employee("Ole","Birk 11", 15555.0));
        em.persist(new Employee("Jens","Cvej 22", 16666.0));
        
        em.getTransaction().commit();
       
        } finally {
            em.close();
        }

        
}
}
