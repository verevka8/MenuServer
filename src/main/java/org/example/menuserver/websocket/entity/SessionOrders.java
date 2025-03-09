package org.example.menuserver.websocket.entity;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionOrders {

    private Map<String, List<Order>> allOrders = new HashMap<>();

    public SessionOrders() {}

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

    public void setAllOrders(Map<String, List<Order>> allOrders) {
        this.allOrders = allOrders;
    }
}
