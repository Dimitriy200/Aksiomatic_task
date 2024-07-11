package com.axmtk_task.axmtk_task.models;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity(name = "AppTable")
@Table
public class AppTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appId;

    @Column(name = "userName")
    private String userName;

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

    @Column(name = "contract_status")
    private String contract_status;

    @Column(name = "contract_solution")
    private String contract_solution;

    @Column(name = "contract_data")
    private byte [] contract_data;

    @Column(name = "credit_amount")
    private long credit_amount;

    public AppTable(){}

    public AppTable(String userName,
                    String passport_data,
                    String family_status,
                    String address,
                    String phone_number,
                    String employment_information,
                    String contract_status,
                    String contract_solution,
                    byte[] contract_data,
                    long credit_amount) {

        this.userName = userName;
        this.passport_data = passport_data;
        this.family_status = family_status;
        this.address = address;
        this.phone_number = phone_number;
        this.employment_information = employment_information;
        this.contract_status = contract_status;
        this.contract_solution = contract_solution;
        this.contract_data = contract_data;
        this.credit_amount = credit_amount;
    }

    public long getAppId() {
        return appId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public void setPassport_data(String passport_data) {
        this.passport_data = passport_data;
    }

    public String getFamily_status() {
        return family_status;
    }

    public void setFamily_status(String family_status) {
        this.family_status = family_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmployment_information() {
        return employment_information;
    }

    public void setEmployment_information(String employment_information) {
        this.employment_information = employment_information;
    }

    public String getContract_status() {
        return contract_status;
    }

    public void setContract_status(String contract_status) {
        this.contract_status = contract_status;
    }

    public String getContract_solution() {
        return contract_solution;
    }

    public void setContract_solution(String contract_solution) {
        this.contract_solution = contract_solution;
    }

    public byte[] getContract_data() {
        return contract_data;
    }

    public void setContract_data(byte[] contract_data) {
        this.contract_data = contract_data;
    }

    public long getCredit_amount() {
        return credit_amount;
    }

    public void setCredit_amount(long credit_amount) {
        this.credit_amount = credit_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppTable appTable = (AppTable) o;

        return appId == appTable.appId &&
                credit_amount == appTable.credit_amount &&
                Objects.equals(userName, appTable.userName) &&
                Objects.equals(passport_data, appTable.passport_data) &&
                Objects.equals(family_status, appTable.family_status) &&
                Objects.equals(address, appTable.address) &&
                Objects.equals(phone_number, appTable.phone_number) &&
                Objects.equals(employment_information, appTable.employment_information) &&
                Objects.equals(contract_status, appTable.contract_status) &&
                Objects.equals(contract_solution, appTable.contract_solution) &&
                Objects.deepEquals(contract_data, appTable.contract_data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId,
                userName,
                passport_data,
                family_status,
                address,
                phone_number,
                employment_information,
                contract_status,
                contract_solution,
                Arrays.hashCode(contract_data),
                credit_amount);
    }
}