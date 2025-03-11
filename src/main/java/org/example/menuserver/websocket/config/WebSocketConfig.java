package org.example.menuserver.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${rabbitmq.host.name}")
    private String rabbitMqHost;

    @Value("${rabbitmq.host.port}")
    private int rabbitMqHostPort;

    @Value("${rabbitmq.usr}")
    private String clientUsr;

    @Value("${rabbitmq.psw}")
    private String clientPsw;
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic","/queue")
                .setRelayHost(rabbitMqHost)
                .setRelayPort(rabbitMqHostPort)
                .setClientLogin(clientUsr)
                .setClientPasscode(clientPsw);

        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/gs").setAllowedOrigins("*");
       System.out.println("STOMP endpoint registered");
    }

}
