package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
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
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    @GetMapping("/{company_id}")
    public Optional<Company> getCompanyById(
            @PathVariable("company_id") Integer company_id
    ){
        return companyRepository.findById(company_id);
    }
    @PostMapping
    public ResponseEntity<String> createCompany(
            @RequestBody Company company
    ){
        companyRepository.save(company);

        return ResponseEntity.ok().body("company created");
    }
    @PostMapping("/{company_id}")
    public ResponseEntity<String> addVehicle(
            @RequestParam Integer vehicle_Id,
            @PathVariable("company_id") Integer company_id
    ){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle_Id);
        Optional<Company> optionalCompany = companyRepository.findById(company_id);

        if (optionalVehicle.isEmpty()) return ResponseEntity.badRequest().body("Vehicle does not exist");
        if (optionalCompany.isEmpty()) return ResponseEntity.badRequest().body("Company does not exist");

        List<Vehicle> vehicles = optionalCompany.get().getVehicles();
        Vehicle vehicleToAdd = optionalVehicle.get();
        vehicles.add(vehicleToAdd);


        return companyRepository.findById(company_id).map(
                company -> {
                    company.setVehicles(vehicles);
                    companyRepository.save(company);
                    return ResponseEntity.ok("Vehicle added to the company " + company.getName());
                }
        ).orElseGet(() -> ResponseEntity.badRequest().body("Company does not exist"));



    }
    @PutMapping("/{company_id}")
    public Company updateCompany(
            @PathVariable("company_id") Integer company_id,
            @RequestBody Company updated_company
    ){
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
    ) throws Exception{
        try {
            companyRepository.deleteById(company_id);
            return ResponseEntity.ok().body("Succesfully deleted the company");
        } catch (Error error){
            throw new Exception(error.getMessage());
        }
    }


}
