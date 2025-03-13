package org.example.menuserver.websocket.controller;

import jakarta.servlet.http.HttpSession;
import org.example.menuserver.redis.RedisMessagePublisher;
import org.example.menuserver.sessions.Session;
import org.example.menuserver.sessions.SessionsController;
import org.example.menuserver.websocket.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Controller
public class WebSocketsController {

    private final SessionsController sessionController;

    @Autowired
    public WebSocketsController(SessionsController sessionController,  RedisMessagePublisher publisher) {
        this.sessionController = sessionController;
    }

    @MessageMapping("/addFood")
    public void receivingMessages(Message<Order> message) {
        var nativeHeaders = message.getHeaders().get("nativeHeaders", LinkedMultiValueMap.class);
        if (nativeHeaders == null || nativeHeaders.get("sessionId") == null || nativeHeaders.get("sessionId").isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Не указан обязательный заголовок сессии");
        }

        String sessionId = nativeHeaders.get("sessionId").get(0).toString();
        Order food = message.getPayload();
        food.setUser(message.getHeaders().get("simpSessionId").toString());
        sessionController.receivingMessages(sessionId, food, true);
    }
}
