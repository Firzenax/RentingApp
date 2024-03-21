package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.CompanyVehicle;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.CompanyVehicleRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/company_vehicles")
public class CompanyVehicleController {

    private final CompanyVehicleRepository companyVehicleRepository;

    private final VehicleRepository vehicleRepository;

    private final CompanyRepository companyRepository;

    public CompanyVehicleController(CompanyVehicleRepository companyVehicleRepository, VehicleRepository vehicleRepository, CompanyRepository companyRepository) {
        this.companyVehicleRepository = companyVehicleRepository;
        this.vehicleRepository = vehicleRepository;
        this.companyRepository = companyRepository;
    }
    @GetMapping
    public List<CompanyVehicle> getAllCompanyVehicles(){
        return companyVehicleRepository.findAll();
    }
    @GetMapping("/{company_vehicle_id}")
    public Optional<CompanyVehicle> getCompanyVehicleById(
            @PathVariable("company_vehicle_id") Integer company_vehicle_id
    ){
        return companyVehicleRepository.findById(company_vehicle_id);
    }

    @PostMapping
    @Transactional
    public CompanyVehicle addVehicle(
            @RequestParam Integer vehicle_Id,
            @RequestParam Integer company_id
    ) throws Exception {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle_Id);
        Optional<Company> optionalCompany = companyRepository.findById(company_id);

        if (optionalVehicle.isEmpty()) throw new Exception("Vehicle does not exist");
        if (optionalCompany.isEmpty()) throw new Exception("Company does not exist");

        Vehicle vehicle = optionalVehicle.get();
        Company company = optionalCompany.get();

        CompanyVehicle companyVehicle = new CompanyVehicle();

        companyVehicle.setVehicle(vehicle);
        companyVehicle.setCompany(company);

        return companyVehicleRepository.save(companyVehicle);
    }
}
