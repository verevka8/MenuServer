package org.example.menuserver.sessions;

import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;

import java.io.Serializable;


public class Session {
    private String sessionId;
    private SessionOrders orders;

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.orders = new SessionOrders();
    }

    public void receiveMessage(Order order) {
        orders.addOrder(order);
    }

    public SessionOrders getOrders(){
        return orders;
    }

    public String getSessionId() {
        return sessionId;
    }
}
