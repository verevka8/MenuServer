package org.example.menuserver;

import org.example.menuserver.sessions.SessionsController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MenuServerApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(MenuServerApplication.class, args);
        SessionsController controller = context.getBean(SessionsController.class);
        controller.createNewSession("1234",true);
    }
}
