package org.example.menuserver.rest.entity;


public class Dish{
    private String id;
    private String name;
    private String urlOfDishImage;
    private int price;

    public Dish(String id, String name, String urlOfDishImage, int price) {
        this.id = id;
        this.name = name;
        this.urlOfDishImage = urlOfDishImage;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getUrlOfDishImage() {
        return urlOfDishImage;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }
}
