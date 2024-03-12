package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vehicle_id;
    private String vehicleName;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "companies_vehicles",
            joinColumns = {
                    @JoinColumn(name = "vehicle_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "company_id")
            }
    )
    @JsonIgnore
    private List<Company> companies;

    public Vehicle() {
    }

    public Vehicle(Integer id, String vehicleName, Brand brand, List<Company> companies) {
        this.vehicle_id = id;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.companies = companies;
    }

    public Integer getId() {
        return vehicle_id;
    }

    public void setId(Integer id) {
        this.vehicle_id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
