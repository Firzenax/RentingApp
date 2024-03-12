package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Brand;
import com.codinglouis.rentingapp.models.Company;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.BrandRepository;
import com.codinglouis.rentingapp.repositories.CompanyRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final BrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;


    public VehicleController(BrandRepository brandRepository, VehicleRepository vehicleRepository, CompanyRepository companyRepository) {
        this.brandRepository = brandRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
    @GetMapping("/{vehicle_id}")
    public Optional<Vehicle> getVehicleById(
            @PathVariable("vehicle_id") Integer vehicle_id
    ){
        return vehicleRepository.findById(vehicle_id);
    }
    @PostMapping
    public ResponseEntity<String> createVehicle(
            @RequestBody Vehicle vehicle,
            @RequestParam Integer brand_id
    ){
        Optional<Brand> optionalBrand = brandRepository.findById(brand_id);
        if(optionalBrand.isEmpty()){
            return ResponseEntity.badRequest().body("Brand not found");
        }

        Brand brand = optionalBrand.get();
        vehicle.setBrand(brand);

        vehicleRepository.save(vehicle);

        return ResponseEntity.ok("Vehicle created");
    }

    @PutMapping("/{vehicle_id}")
    public Vehicle updatVehicle(
            @PathVariable("vehicle_id") Integer vehicle_id,
            @RequestBody Vehicle updated_vehicle
    ){
        return vehicleRepository.findById(vehicle_id).map(vehicle -> {
            vehicle.setVehicleName(updated_vehicle.getVehicleName());
            return vehicleRepository.save(vehicle);
        }).orElseGet(() -> {
            updated_vehicle.setId(vehicle_id);
            return vehicleRepository.save(updated_vehicle);
        });


    }
    @DeleteMapping
    public ResponseEntity<String> deleteVehicle(
            @PathVariable("vehicle_id") Integer vehicle_id
    ) throws Exception {
        try {
            vehicleRepository.deleteById(vehicle_id);
            return ResponseEntity.ok("Vehicle deleted");
        }
        catch (Error error){
            throw new Exception(error.getMessage());
        }
    }
}
