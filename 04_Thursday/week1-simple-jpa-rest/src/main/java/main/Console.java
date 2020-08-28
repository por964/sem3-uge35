/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.Employee;
import facades.EmployeeFacade;
import static facades.EmployeeFacade.getAllEmployees;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author claes
 */
public class Console {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        
        Employee emp1 = facade.createEmployee("Claes", "Kvej 53", 12222.0);
        Employee emp2 = facade.createEmployee("Niels", "Avej 22", 13333.0);
        Employee emp3 = facade.createEmployee("Hans", "Birk 33", 14444.0);
        Employee emp4 = facade.createEmployee("Ole", "Kildevej 44", 15555.0);
        Employee emp5 = facade.createEmployee("Jens", "Sommervej 55", 16666.0);
        
        
        //System.out.println(getEmployeeByID(2));
        
        //System.out.println(getEmployeesByName("Claes"));
        
        //getAllEmployees().stream().forEach(System.out::println);
        
        System.out.println(getAllEmployees());
        
        //System.out.println(getEmpHiSalary());
    
}
}
