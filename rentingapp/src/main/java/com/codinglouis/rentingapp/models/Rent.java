package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rent_id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyVehicle_id")
    @JsonIgnore
    private CompanyVehicle companyVehicle;
    private LocalDate rentStartDate;
    private LocalDate rendEndDate;


    public Rent(Integer rent_id, User user, Company company, CompanyVehicle companyVehicle, LocalDate rentStartDate, LocalDate rendEndDate) {
        this.rent_id = rent_id;
        this.user = user;
        this.companyVehicle = companyVehicle;
        this.rentStartDate = rentStartDate;
        this.rendEndDate =rendEndDate;
    }

    public Rent() {

    }

    public Integer getRent_id() {
        return rent_id;
    }

    public void setRent_id(Integer rent_id) {
        this.rent_id = rent_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(LocalDate rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public LocalDate getRendEndDate() {
        return rendEndDate;
    }

    public void setRendEndDate(LocalDate rendEndDate) {
        this.rendEndDate = rendEndDate;
    }

    public CompanyVehicle getCompanyVehicle() {
        return companyVehicle;
    }

    public void setCompanyVehicle(CompanyVehicle companyVehicle) {
        this.companyVehicle = companyVehicle;
    }
}
