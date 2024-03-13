package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    private final VehicleRepository vehicleRepository;

    public CompanyController(CompanyRepository companyRepository, VehicleRepository vehicleRepository) {
        this.companyRepository = companyRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{company_id}")
    public Optional<Company> getCompanyById(
            @PathVariable("company_id") Integer company_id
    ) {
        return companyRepository.findById(company_id);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(
            @RequestBody Company company
    ) {
        companyRepository.save(company);

        return ResponseEntity.ok().body("company created");
    }


    @PostMapping("/{company_id}")
    @Transactional
    public Company addVehicle(
            @RequestParam Integer vehicle_Id,
            @PathVariable("company_id") Integer company_id
    ) throws Exception {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle_Id);
        Optional<Company> optionalCompany = companyRepository.findById(company_id);

        if (optionalVehicle.isEmpty()) throw new Exception("Vehicle does not exist");
        if (optionalCompany.isEmpty()) throw new Exception("Company does not exist");

        Company company = optionalCompany.get();
        Vehicle vehicle = optionalVehicle.get();

        // Add vehicle to the company's list of vehicles
        company.getVehicles().add(vehicle);

        // Add company to the vehicle's list of companies
        vehicle.getCompanies().add(company);

        companyRepository.save(company);

        return company;
    }

    @PutMapping("/{company_id}")
    public Company updateCompany(
            @PathVariable("company_id") Integer company_id,
            @RequestBody Company updated_company
    ) {
        return companyRepository.findById(company_id).map(company -> {
            company.setName(updated_company.getName());
            return companyRepository.save(company);
        }).orElseGet(() -> {
            updated_company.setCompany_id(company_id);
            return companyRepository.save(updated_company);
        });


    }

    @DeleteMapping("/{company_id}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable("company_id") Integer company_id
    ) throws Exception {
        try {
            companyRepository.deleteById(company_id);
            return ResponseEntity.ok().body("Succesfully deleted the company");
        } catch (Error error) {
            throw new Exception(error.getMessage());
        }
    }

    @DeleteMapping("/{company_id}/vehicles/{vehicle_id}")
    @Transactional
    public ResponseEntity<String> removeVehicleFromCompany(
            @PathVariable("company_id") Integer companyId,
            @PathVariable("vehicle_id") Integer vehicleId
    ) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalCompany.isEmpty()) return ResponseEntity.badRequest().body("Company does not exist");
        if (optionalVehicle.isEmpty()) return ResponseEntity.badRequest().body("Vehicle does not exist");

        Company company = optionalCompany.get();
        Vehicle vehicle = optionalVehicle.get();

        // Remove the vehicle from the company's list of vehicles
        if (!company.getVehicles().remove(vehicle)) {
            return ResponseEntity.badRequest().body("Vehicle is not associated with the company");
        }

        // Remove the company from the vehicle's list of companies
        vehicle.getCompanies().remove(company);

        companyRepository.save(company);

        return ResponseEntity.ok("Vehicle removed from the company " + company.getName());
    }
}
