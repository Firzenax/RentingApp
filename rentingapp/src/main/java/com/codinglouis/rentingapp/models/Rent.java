package com.codinglouis.rentingapp.models;

import jakarta.persistence.*;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rent_id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Rent() {
    }

    public Rent(Integer rent_id, User user, Company company) {
        this.rent_id = rent_id;
        this.user = user;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
