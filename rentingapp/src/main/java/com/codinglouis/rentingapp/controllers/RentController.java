package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.Rent;
import com.codinglouis.rentingapp.models.User;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.RentRepository;
import com.codinglouis.rentingapp.repositories.UserRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentRepository rentRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public RentController(RentRepository rentRepository, CompanyRepository companyRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping
    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }
    @GetMapping("{rent_id}")
    public Optional<Rent> getRentById(
            @PathVariable("rent_id") Integer rent_id
    ) throws Exception{
        try {
            return rentRepository.findById(rent_id);
        }catch (Error error){
            throw new Exception(error.getMessage() + "rent didn't exist");
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createRent(
            @RequestParam Integer company_id,
            @RequestParam Integer vehicle_id,
            @RequestParam Integer user_id
    ) {
        Optional<Company> optionalCompany = companyRepository.findById(company_id);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle_id);
        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalCompany.isEmpty()) return ResponseEntity.badRequest().body("Company does not exist");
        if (optionalVehicle.isEmpty()) return ResponseEntity.badRequest().body("Vehicle does not exist");
        if (optionalUser.isEmpty()) return ResponseEntity.badRequest().body("User does not exist");

        Company company = optionalCompany.get();
        Vehicle vehicle = optionalVehicle.get();
        User user = optionalUser.get();

        // Check if the vehicle is associated with the company
        if (!company.getVehicles().contains(vehicle)) {
            return ResponseEntity.badRequest().body("Vehicle is not associated with the company");
        }

        Rent rent = new Rent();
        rent.setCompany(company);
        rent.setVehicle(vehicle);
        rent.setUser(user);

        rentRepository.save(rent);

        return ResponseEntity.ok("Rent created successfully");
    }

    @DeleteMapping("{rent_id}")
    public ResponseEntity<String> deleteRent(
            @PathVariable("rent_id") Integer rent_id
    ) throws  Exception{
        try {
            rentRepository.deleteById(rent_id);
            return ResponseEntity.ok().body("Rent deleted");
        }
        catch (Error error){
            throw new Exception("Error during rent deletion : " + error.getMessage());
        }
    }
}
