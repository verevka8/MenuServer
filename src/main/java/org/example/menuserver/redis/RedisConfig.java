package org.example.menuserver.redis;


import org.example.menuserver.websocket.entity.OrderMessage;
import org.example.menuserver.websocket.entity.SessionCreateMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${redis.host.name}")
    private String redisHost;

    @Value("${redis.host.port}")
    private int redisHostPort;

    @Value("${redis.psw}")
    private String clientPsw;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisHostPort);
        config.setPassword(clientPsw);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public MessageListenerAdapter orderListenerAdapter(RedisMessageSubscriber subscriber) {
        Jackson2JsonRedisSerializer<OrderMessage> serializer = new Jackson2JsonRedisSerializer<>(OrderMessage.class);
        MessageListenerAdapter adapter = new MessageListenerAdapter(subscriber, "handleOrderMessage");
        adapter.setSerializer(serializer);
        return adapter;
    }

    @Bean
    public MessageListenerAdapter sessionListenerAdapter(RedisMessageSubscriber subscriber) {
        Jackson2JsonRedisSerializer<SessionCreateMessage> serializer = new Jackson2JsonRedisSerializer<>(SessionCreateMessage.class);
        MessageListenerAdapter adapter = new MessageListenerAdapter(subscriber, "handleSessionUpdate");
        adapter.setSerializer(serializer);
        return adapter;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(MessageListenerAdapter orderListenerAdapter, MessageListenerAdapter sessionListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());

        container.addMessageListener(orderListenerAdapter, new ChannelTopic("orderChannel"));
        container.addMessageListener(sessionListenerAdapter,new ChannelTopic("sessionChannel"));
        return container;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("test");
    }
}
