/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Customer;

import java.util.ArrayList;

/**
 *
 * @author harold
 */
public class CustomerDirectory {
    
    private ArrayList<Customer> customerDirectory;
    
    public CustomerDirectory() {
        
        customerDirectory = new ArrayList();
        
    }

    public ArrayList<Customer> getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(ArrayList<Customer> customerDirectory) {
        this.customerDirectory = customerDirectory;
    }
    
    public Customer newCustomer(String name, String email, String phoneNumber, int age, String homeAddress, String userName){
        Customer customer = new Customer(name, email, phoneNumber, age, homeAddress, userName);
        customerDirectory.add(customer);
        return customer;
    }
    
    public void removeCustomer(Customer customer){
        customerDirectory.remove(customer);
    }
    
    public Customer getCustomer(String name){
        for(Customer customer: customerDirectory){
            if(customer.getName().equalsIgnoreCase(name)){
                return customer;
            }
        }
        return null;
    }
    
}
