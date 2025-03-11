package org.example.menuserver.redis;

import org.example.menuserver.sessions.SessionsController;
import org.example.menuserver.websocket.entity.OrderMessage;
import org.example.menuserver.websocket.entity.SessionCreateMessage;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class RedisMessageSubscriber {

    private SessionsController controller;

    @Autowired
    public void setMessagingTemplate(SessionsController controller) {
        this.controller = controller;
    }

    public void handleSessionUpdate(SessionCreateMessage message) {
        System.out.println("Обновление сессии: " + message);
        controller.createNewSession(message.getSessionId(),false);
    }

    public void handleOrderMessage(OrderMessage message) {
        System.out.println("Новый заказ: " + message);
        controller.receivingMessages(message.getSessionId(),message.getOrder(),false);
        System.out.println("Заказ получен, синхронизируюсь");
    }

}
