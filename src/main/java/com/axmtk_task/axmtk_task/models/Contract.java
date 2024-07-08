package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Contract")
public class Contract {

    @Id
//    @SequenceGenerator(name = "customer_seq", initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "contract_id")
    public long contract_id;

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

    public long getContract_id() {
        return contract_id;
    }

    public boolean isContract_status() {
        return contract_status;
    }

    public byte getContract_data() {
        return contract_data;
    }

    public long getCredit_amount() {
        return credit_amount;
    }

    public void setContract_id(long contract_id) {
        this.contract_id = contract_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contract_id == contract.contract_id && contract_status == contract.contract_status && contract_data == contract.contract_data && credit_amount == contract.credit_amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contract_id, contract_status, contract_data, credit_amount);
    }
}