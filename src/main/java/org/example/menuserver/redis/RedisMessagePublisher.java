package org.example.menuserver.redis;

import org.example.menuserver.sessions.Session;
import org.example.menuserver.websocket.entity.OrderMessage;
import org.example.menuserver.websocket.entity.SessionCreateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(OrderMessage message) {
        System.out.println("Заказ отправлен на синхр");
        redisTemplate.convertAndSend("orderChannel", message);
    }
    public void publish(SessionCreateMessage session){
        redisTemplate.convertAndSend("sessionChannel",session);
    }
}