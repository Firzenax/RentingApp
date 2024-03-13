package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer company_id;
    private String name;
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Rent> rents;
    @ManyToMany(mappedBy = "companies")
    private List<Vehicle> vehicles;

    public Company() {
    }

    public Company(Integer company_id, String name, List<Rent> rents, List<Vehicle> vehicles) {
        this.company_id = company_id;
        this.name = name;
        this.rents = rents;
        this.vehicles = vehicles;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
