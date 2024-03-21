package com.codinglouis.rentingapp.controllers;

import com.codinglouis.rentingapp.models.Brand;
import com.codinglouis.rentingapp.models.Vehicle;
import com.codinglouis.rentingapp.repositories.BrandRepository;
import com.codinglouis.rentingapp.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/brands")
public class BrandController {
    private final BrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;

    public BrandController(BrandRepository brandRepository, VehicleRepository vehicleRepository) {
        this.brandRepository = brandRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @GetMapping("/{brand_id}")
    public Optional<Brand> getBrandById(
            @PathVariable("brand_id") Integer brand_id
    ) {
        return brandRepository.findById(brand_id);
    }

    @PostMapping
    @Transactional
    public Brand createBrand(
            @RequestBody Brand brand
    ) {
        if (brand.getVehicles() != null) brand.getVehicles().stream().map(vehicleRepository::save);
        return brandRepository.save(brand);
    }

    @PutMapping("/{brand_id}")
    @Transactional
    public Brand updateBrand(
            @RequestBody Brand updated_brand,
            @PathVariable("brand_id") Integer brand_id
    ) {
        return brandRepository.findById(brand_id).map(brand -> {
            brand.setBrandName(updated_brand.getBrandName());
            brand.setVehicles(updated_brand.getVehicles());
            return brandRepository.save(brand);
        }).orElseGet(() -> {
            updated_brand.setId(brand_id);
            return brandRepository.save(updated_brand);
        });
    }
    @DeleteMapping("/{brand_id}")
    @Transactional
    public String deleteBrand(
            @PathVariable("brand_id") Integer brand_id
    ) throws Exception {
        try {
            brandRepository.deleteById(brand_id);
            return "Brand deleted";
        } catch (Error error){
            throw new Exception(error.getMessage());
        }
    }


}
