package org.example.menuserver.rest.controller;

import org.example.menuserver.rest.entity.Dish;
import org.example.menuserver.sessions.SessionsController;
import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    private SessionsController sessionsController;

    @Autowired
    public ApiController(SessionsController sessionsController){
        this.sessionsController = sessionsController;
    }

    @GetMapping("/orders/{id}")
    public SessionOrders GetAllOrders(@PathVariable("id") String sessionId){
        return sessionsController.GetAllSessionOrders(sessionId);
    }

    @GetMapping("/menu")
    public List<Dish> GetAllOrders(){
        ArrayList<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < 10;i++){
            dishes.add(new Dish("bigHit","Биг-хит","https://storage.yandexcloud.net/menu-app-image-backet/test.jpg",257));
            dishes.add(new Dish("chickenBurger","Чикен-бургер","https://storage.yandexcloud.net/menu-app-image-backet/chickenburger.jpg",93));
        }
        return dishes;
    }
}