package org.example.menuserver.sessions;

import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Session implements Runnable {
    private String sessionId;
    private final BlockingQueue<Order> messageQueue;
    private final SimpMessagingTemplate messagingTemplate;
    private SessionOrders sessionOrders;

    public Session(String sessionId,SimpMessagingTemplate messagingTemplate) {
        this.messageQueue = new LinkedBlockingQueue<>();
        this.sessionId = sessionId;
        this.messagingTemplate = messagingTemplate;
        sessionOrders = new SessionOrders();
    }

    @Override
    public void run() {

        while(true){
            try {
                Order order = messageQueue.take();
                sessionOrders.addOrder(order);
                System.out.println("В сессии: " +  sessionId + " получено новое сообщение: " + order);
                sendMessage(order);
                System.out.println("Сообщение всем отправлено");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void receiveMessage(Order message) {
        messageQueue.offer(message);
    }

    public SessionOrders GetAllOrders(){
        return sessionOrders; //TODO: сделать копию
    }

    private void sendMessage(Order message){
        messagingTemplate.convertAndSend("/topic/session/" + sessionId,message);
    }
}
