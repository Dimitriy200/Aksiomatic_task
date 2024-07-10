package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Contract")
@Table
public class Contract {

    @Id
//    @SequenceGenerator(name = "customer_seq", initialValue = 1)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "contract_id")
    private long contract_id;

    @Column(name = "contract_status")
    private String contract_status;

    @Column(name = "contract_data")
    private byte [] contract_data;

    @Column(name = "credit_amount")
    private Long credit_amount;

    public Contract(byte [] contract_data,
                    long credit_amount,
                    String contract_status) {
        this.contract_status = contract_status;
        this.contract_data = contract_data;
        this.credit_amount = credit_amount;
    }

    public Contract(){}

    public Long getContract_id() {
        Long res = contract_id;
        return res;
    }

    public String getContract_status() {
        return contract_status;
    }

    public byte [] getContract_data() {
        return contract_data;
    }

    public Long getCredit_amount() {
        Long res = credit_amount;
        return res;
    }

    public void setContract_id(long contract_id) {
        this.contract_id = contract_id;
    }

    public void setContract_status(String contract_status) {
        this.contract_status = contract_status;
    }

    public void setContract_data(byte[] contract_data) {
        this.contract_data = contract_data;
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