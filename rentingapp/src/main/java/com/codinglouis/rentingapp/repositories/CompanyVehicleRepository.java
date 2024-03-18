package com.codinglouis.rentingapp.repositories;

import com.codinglouis.rentingapp.models.CompanyVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyVehicleRepository extends JpaRepository<CompanyVehicle, Integer> {
}