package com.codinglouis.rentingapp.repositories;

import com.codinglouis.rentingapp.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
