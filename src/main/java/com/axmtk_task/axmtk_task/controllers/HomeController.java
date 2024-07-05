package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.services.UserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequestMapping("/")
public class HomeController {

    @GetMapping("/login")
    public Client login(){
        Client client = new Client();

        UserManager userManager = new UserManager();
        userManager.init();
        userManager.addUser(client);

        return client;
    }
}