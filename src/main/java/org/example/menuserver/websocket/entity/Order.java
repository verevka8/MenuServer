package org.example.menuserver.websocket.entity;


public class Order {
    private String idOfFood;
    private String user;

    public Order(String idOfFood, String user) {
        this.idOfFood = idOfFood;
        this.user = user;
    }

    public String getIdOfFood() {
        return idOfFood;
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
