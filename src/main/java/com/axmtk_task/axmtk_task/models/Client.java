package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Client")
public class Client {

    @Id
    @GeneratedValue( strategy = SEQUENCE )
    private Long person_id;

    @Column(name = "client_name")
    private final String client_name;

    @Column(name = "passport_data")
    private final String passport_data;

    @Column(name = "family_status")
    private final String family_status;

    @Column(name = "address")
    private final String address;

    @Column(name = "phone_number")
    private final String phone_number;

    @Column(name = "employment_information")
    private final String employment_information;

    @Column(name = "credit_amount")
    private final String credit_amount;

    @ManyToOne
    @JoinColumn(name = "contract_id",
            foreignKey = @ForeignKey(name = "contract_id_FK"))
    private Contract сontract;


    public Client(String client_name,
                  String passport_data,
                  String family_status,
                  String address,
                  String phone_number,
                  String employment_information,
                  String credit_amount,
                  Contract сontract) {

        this.client_name = client_name;
        this.passport_data = passport_data;
        this.family_status = family_status;
        this.address = address;
        this.phone_number = phone_number;
        this.employment_information = employment_information;
        this.credit_amount = credit_amount;
        this.сontract = сontract;
    }
}