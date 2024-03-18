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
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<CompanyVehicle> companyVehicles;

    public Company() {
    }

    public Company(Integer company_id, String name, List<CompanyVehicle> companyVehicles) {
        this.company_id = company_id;
        this.name = name;
        this.companyVehicles = companyVehicles;
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

    public List<CompanyVehicle> getCompanyVehicles() {
        return companyVehicles;
    }

    public void setCompanyVehicles(List<CompanyVehicle> companyVehicles) {
        this.companyVehicles = companyVehicles;
    }
}
