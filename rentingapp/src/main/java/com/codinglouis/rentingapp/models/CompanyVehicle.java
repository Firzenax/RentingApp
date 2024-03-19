package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CompanyVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyVehicle_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_id")
    @JsonIgnore
    private Rent rent;

    public CompanyVehicle() {
    }

    public CompanyVehicle(Integer companyVehicle_id, Company company, Vehicle vehicle, Rent rent) {
        this.companyVehicle_id = companyVehicle_id;
        this.company = company;
        this.vehicle = vehicle;
        this.rent = rent;
    }

    public Integer getCompanyVehicle_id() {
        return companyVehicle_id;
    }

    public void setCompanyVehicle_id(Integer companyVehicle_id) {
        this.companyVehicle_id = companyVehicle_id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
