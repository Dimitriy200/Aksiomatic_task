package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Entity(name = "User")
public class User {

    @Id
    private AtomicLong id;

    public User(){}
}