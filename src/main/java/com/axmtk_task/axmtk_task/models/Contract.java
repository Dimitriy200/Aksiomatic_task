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

    @Column(name = "contract_data")
    private final Byte contract_data;

    @Column(name = "credit_amount")
    private final String credit_amount;

    public Contract(Byte contract_data, String credit_amount) {
        this.status = false;
        this.contract_data = contract_data;
        this.credit_amount = credit_amount;
    }
}