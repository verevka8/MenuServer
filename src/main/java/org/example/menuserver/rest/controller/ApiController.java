package org.example.menuserver.rest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.menuserver.rest.entity.Dish;
import org.example.menuserver.sessions.SessionsController;
import org.example.menuserver.websocket.entity.Order;
import org.example.menuserver.websocket.entity.SessionOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    private final SessionsController sessionsController;
    private List<Dish> menu;

    @Autowired
    public ApiController(SessionsController sessionsController){
        this.sessionsController = sessionsController;
        try (var inputStream = ApiController.class.getResourceAsStream("/data.json")) {
            if (inputStream == null) {
                throw new IllegalStateException("Файл data.json не найден");
            }

            Gson gson = new Gson();
            String json = new String(inputStream.readAllBytes());
            menu = gson.fromJson(json,new TypeToken<List<Dish>>() {}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/orders/{id}")
    public SessionOrders GetAllOrders(@PathVariable("id") String sessionId){
        return sessionsController.GetAllSessionOrders(sessionId);
    }

    @GetMapping("/menu")
    public List<Dish> GetAllOrders(){
        return menu;
    }
}