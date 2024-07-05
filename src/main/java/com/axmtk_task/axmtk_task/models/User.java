package com.axmtk_task.axmtk_task.models;

import org.springframework.stereotype.Component;

@Component
public class User {
    private char name[];

    public User(char[] name){
        this.name = name;
    }
}