package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {

    @GetMapping("/login")
    public Object login(){
        char[] nm = {'U', 's', '1'};
        return new User(nm);
    }
}