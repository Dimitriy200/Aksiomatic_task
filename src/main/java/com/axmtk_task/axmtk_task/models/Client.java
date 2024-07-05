package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Client")
//@Table()
public class Client {

    @Id
    @GeneratedValue( strategy = SEQUENCE )
    private Long id;

//    @Column(name = "name")
//    private String name;

    public Client(){
//        this.name = name;
    }
}