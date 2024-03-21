package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.*;
import com.codinglouis.rentingapp.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/rents")
public class RentController {

    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    private final CompanyVehicleRepository companyVehicleRepository;


    public RentController(RentRepository rentRepository, UserRepository userRepository, CompanyVehicleRepository companyVehicleRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.companyVehicleRepository = companyVehicleRepository;
    }
    @GetMapping
    public List<Rent> getAllRents(){
        return rentRepository.findAll();
    }
    @GetMapping("/{rent_id}")
    public Optional<Rent> getRentById(
            @PathVariable("rent_id") Integer rent_id
    ) throws Exception{
        try {
            return rentRepository.findById(rent_id);
        }catch (Error error){
            throw new Exception(error.getMessage() + "rent didn't exist");
        }

    }
    @PostMapping("/user/{user_id}")
    @Transactional
    public Rent createRent(
            @PathVariable("user_id") Integer user_id,
            @RequestParam Integer company_vehicle_id,
            @RequestBody RentDates rentDates
            ) throws Exception{
        Optional<User> optionalUser = userRepository.findById(user_id);
        Optional<CompanyVehicle> optionalCompanyVehicle = companyVehicleRepository.findById(company_vehicle_id);

        if(optionalUser.isEmpty()) throw new Exception("No user with this id");
        if(optionalCompanyVehicle.isEmpty()) throw new Exception("No company vehicle with this id");

        User user = optionalUser.get();
        CompanyVehicle companyVehicle = optionalCompanyVehicle.get();

        Rent rent = new Rent();

        rent.setUser(user);
        rent.setCompanyVehicle(companyVehicle);
        rent.setRentStartDate(rentDates.getRentStartDateStr());
        rent.setRendEndDate(rentDates.getRentEndDateStr());

        companyVehicle.setRent(rent);

        return rentRepository.save(rent);


    }
}
