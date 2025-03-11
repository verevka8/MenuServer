package org.example.menuserver.websocket.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderMessage implements Serializable {
    private String sessionId;
    private Order order;

    @JsonCreator
    public OrderMessage(@JsonProperty("sessionId") String sessionId, @JsonProperty("order") Order order) {
        this.sessionId = sessionId;
        this.order = order;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
