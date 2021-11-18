/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Order;

import java.util.ArrayList;

/**
 *
 * @author prasanna
 */
public class OrderDirectory {
    private ArrayList<Order> orderDirectory;
    
    public OrderDirectory() {
        orderDirectory = new ArrayList();
    }

    public ArrayList<Order> getOrderDirectory() {
        return orderDirectory;
    }

    public void setOrderDirectory(ArrayList<Order> orderDirectory) {
        this.orderDirectory = orderDirectory;
    }
    
    public Order newOrder(){
        Order order = new Order();
        orderDirectory.add(order);
        return order;
    }
    
    public Order getOrderId(String id){
        for(Order order: orderDirectory){
            if(order.getOrderId().equals(id)){
                return order;
            }
        }
        return null;
    }
    
}
