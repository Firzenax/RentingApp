package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.CompanyVehicle;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.CompanyVehicleRepository;
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

    private final CompanyVehicleRepository companyVehicleRepository;
    private final VehicleRepository vehicleRepository;

    public CompanyController(CompanyRepository companyRepository, CompanyVehicleRepository companyVehicleRepository,
                             VehicleRepository vehicleRepository) {
        this.companyRepository = companyRepository;
        this.companyVehicleRepository = companyVehicleRepository;
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
            @PathVariable("vehicle_id") Integer companyVehicleId
    ) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        Optional<CompanyVehicle> optionalCompanyVehicle = companyVehicleRepository.findById(companyVehicleId);

        if (optionalCompany.isEmpty()) return ResponseEntity.badRequest().body("Company does not exist");
        if (optionalCompanyVehicle.isEmpty()) return ResponseEntity.badRequest().body("Vehicle does not exist");

        Company company = optionalCompany.get();
        CompanyVehicle companyVehicle = optionalCompanyVehicle.get();

        // Remove the vehicle from the company's list of vehicles
        if (!company.getCompanyVehicles().remove(companyVehicle)) {
            return ResponseEntity.badRequest().body("Vehicle is not associated with the company");
        }

        // Remove the company from the vehicle's list of companies
        companyRepository.delete(companyVehicle.getCompany());

        companyRepository.save(company);

        return ResponseEntity.ok("Vehicle removed from the company " + company.getName());
    }
}
