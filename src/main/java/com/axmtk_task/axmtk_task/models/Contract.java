package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Contract")
public class Contract {

    @Id
    @GeneratedValue( strategy = SEQUENCE )
    private Long contract_id;

    @Column(name = "status")
    private final Boolean status;

    @Column(name = "document")
    private final Byte document;

    public Contract(Byte document) {
        this.status = false;
        this.document = document;
    }
}