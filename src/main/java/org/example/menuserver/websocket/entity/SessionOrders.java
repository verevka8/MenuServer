package org.example.menuserver.websocket.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SessionOrders {

    private final Map<String, List<Order>> allOrders;

    public SessionOrders() {
        allOrders = new HashMap<>();
    }

    public void addOrder(Order newOrder){
        if(allOrders.containsKey(newOrder.getUser())){
            allOrders.get(newOrder.getUser()).add(newOrder);
        }else{
            allOrders.put(newOrder.getUser(),new ArrayList<>(){
                {
                    add(newOrder);
                }
            });
        }
    }

    public Map<String, List<Order>> getAllOrders() {
        return allOrders;
    }
}
