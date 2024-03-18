package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicle_id;
    private String vehicleName;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(
            mappedBy = "vehicle",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<CompanyVehicle> companyVehicles;

    public Vehicle() {
    }

    public Vehicle(Integer vehicle_id, String vehicleName, Brand brand, List<CompanyVehicle> companyVehicles) {
        this.vehicle_id = vehicle_id;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.companyVehicles = companyVehicles;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
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

    public List<CompanyVehicle> getCompanyVehicles() {
        return companyVehicles;
    }

    public void setCompanyVehicles(List<CompanyVehicle> companyVehicles) {
        this.companyVehicles = companyVehicles;
    }
}
