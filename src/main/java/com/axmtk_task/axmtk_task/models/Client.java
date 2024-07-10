package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Client")
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "client_id")
    private long client_id;

    @Column(name = "client_name")
    private String client_name;

    @Column(name = "passport_data")
    private String passport_data;

    @Column(name = "family_status")
    private String family_status;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "employment_information")
    private String employment_information;

    @ManyToOne
    @JoinColumn(name = "contract_id_FK",
            foreignKey = @ForeignKey(name = "contract_id_FK"))
    private Contract contract_id_FK;


    public Client(String client_name,
                  String passport_data,
                  String family_status,
                  String address,
                  String phone_number,
                  String employment_information,
                  Contract contract_id_FK) {

        this.client_name = client_name;
        this.passport_data = passport_data;
        this.family_status = family_status;
        this.address = address;
        this.phone_number = phone_number;
        this.employment_information = employment_information;
        this.contract_id_FK = contract_id_FK;
    }

    public Client(){}

    public long getClient_id() {
        return client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public String getFamily_status() {
        return family_status;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmployment_information() {
        return employment_information;
    }

    public Contract getContract() {
        return contract_id_FK;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public void setContract(Contract contract_id_FK) {
        this.contract_id_FK = contract_id_FK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return client_id == client.client_id && Objects.equals(client_name, client.client_name) &&
                Objects.equals(passport_data, client.passport_data) &&
                Objects.equals(family_status, client.family_status) &&
                Objects.equals(address, client.address) &&
                Objects.equals(phone_number, client.phone_number) &&
                Objects.equals(employment_information, client.employment_information) &&
                Objects.equals(contract_id_FK, client.contract_id_FK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, client_name, passport_data, family_status, address, phone_number, employment_information, contract_id_FK);
    }
}