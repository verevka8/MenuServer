package org.example.menuserver.sessions;

import org.example.menuserver.redis.RedisMessagePublisher;
import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.OrderMessage;
import org.example.menuserver.websocket.entity.SessionCreateMessage;
import org.example.menuserver.websocket.entity.SessionOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SessionsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private final RedisMessagePublisher publisher;

    @Autowired
    public SessionsController(SimpMessagingTemplate messagingTemplate, RedisMessagePublisher publisher){
        this.messagingTemplate = messagingTemplate;
        this.publisher = publisher;
    }

    public void createNewSession(String sessionId, boolean flag){
        Session session = new Session(sessionId);
        sessionMap.put(sessionId,session);
        if (flag){
            publisher.publish(new SessionCreateMessage(sessionId));
        }

    }

    public void receivingMessages(String sessionId, Order order, boolean flag){
        sessionMap.get(sessionId).receiveMessage(order);
        if (flag){
            publisher.publish(new OrderMessage(sessionId,order));
            System.out.println("Выложилось");
            sendMessage(sessionId,order);
        }
    }

    public SessionOrders GetAllSessionOrders(String sessionId) throws IllegalArgumentException{
        if (!sessionMap.containsKey(sessionId)){
            throw new IllegalArgumentException("Сессия " + sessionId + " не найдена");
        }
        return sessionMap.get(sessionId).getOrders();
    }

    private void sendMessage(String sessionId, Order order){
        messagingTemplate.convertAndSend("/topic/session." + sessionId,order);
    }

}
