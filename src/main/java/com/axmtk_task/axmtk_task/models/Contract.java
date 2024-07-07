package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.*;

@Entity(name = "Contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = TABLE)
    private long contract_id;

    @Column(name = "status")
    private final boolean contract_status;

    @Column(name = "contract_data")
    private final byte contract_data;

    @Column(name = "credit_amount")
    private final long credit_amount;

    public Contract(byte contract_data, long credit_amount) {
        this.contract_status = false;
        this.contract_data = contract_data;
        this.credit_amount = credit_amount;
    }
}