package org.example.menuserver.sessions;

import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class SessionsController {
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    public SessionsController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void createNewSession(String sessionId){
        Session session = new Session(sessionId,messagingTemplate);
        sessionMap.put(sessionId,session);
        new Thread(session).start();
    }

    public void receivingMessages(String sessionId, Order message){
        sessionMap.get(sessionId).receiveMessage(message);
    }

    public SessionOrders GetAllSessionOrders(String sessionId) throws IllegalArgumentException{
        if (!sessionMap.containsKey(sessionId)){
            throw new IllegalArgumentException("Сессия " + sessionId + " не найдена");
        }
        return sessionMap.get(sessionId).GetAllOrders();
    }

}
