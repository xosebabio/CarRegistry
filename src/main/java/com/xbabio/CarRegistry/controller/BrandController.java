package com.xbabio.CarRegistry.controller;


import com.xbabio.CarRegistry.controller.dtos.BrandSaveRequest;
import com.xbabio.CarRegistry.domain.Brand;
import com.xbabio.CarRegistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brand")
    public ResponseEntity<?> addBrand(@RequestBody BrandSaveRequest brandRequest) {
        try {
            Brand brand = brandService.mapBrandSaveRequestToEntity(brandRequest);
            brandService.saveBrand(brand);
            log.info("Marca creada: " + brand.toString());
            return new ResponseEntity<>(brand, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear la marca", e);
            return new ResponseEntity<>("Error al crear la marca", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/brand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable Integer id){
        Optional<Brand> brandFound = brandService.getBrand(id);
        if (brandFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        log.info("La marca es: " + brandFound.toString());
        return ResponseEntity.ok(brandFound);
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PutMapping("/brand")
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand){
        try {
            brandService.saveBrand(brand);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id){
        Optional<Brand> brand = brandService.getBrand(id);
        if (brand.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        brandService.deleteBrand(brand.get());
        log.info("La marca con id {} ha sido borrada.", id);
        return ResponseEntity.ok().build();
    }


}
