package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brand_id;
    private String brandName;
    @OneToMany(
            mappedBy = "brand",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Vehicle> vehicles;

    public Brand() {
    }

    public Brand(Integer id, String brandName, List<Vehicle> vehicles) {
        this.brand_id = id;
        this.brandName = brandName;
        this.vehicles = vehicles;
    }

    public Integer getId() {
        return brand_id;
    }

    public void setId(Integer id) {
        this.brand_id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
