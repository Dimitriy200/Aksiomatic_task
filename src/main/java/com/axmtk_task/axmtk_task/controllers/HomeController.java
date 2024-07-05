package com.axmtk_task.axmtk_task.controllers;

import com.axmtk_task.axmtk_task.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@RequestMapping("/")
public class HomeController {

    @GetMapping("/login/{name}")
    public User login(@PathVariable String name){
        return new User(name);
    }
}