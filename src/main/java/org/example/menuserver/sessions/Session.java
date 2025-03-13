package org.example.menuserver.sessions;

import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;


public class Session {
    private final String sessionId;
    private final SessionOrders orders;

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
