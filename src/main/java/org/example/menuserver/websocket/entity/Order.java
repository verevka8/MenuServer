package org.example.menuserver.websocket.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    private String idOfFood;
    private String user;

    @JsonCreator
    public Order(@JsonProperty("idOfFood") String idOfFood,@JsonProperty("user") String user) {
        this.idOfFood = idOfFood;
        this.user = user;
    }

    public String getIdOfFood() {
        return idOfFood;
    }

    public void setIdOfFood(String idOfFood) {
        this.idOfFood = idOfFood;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOfFood='" + idOfFood + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
