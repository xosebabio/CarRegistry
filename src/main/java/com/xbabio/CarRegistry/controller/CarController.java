package com.xbabio.CarRegistry.controller;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import com.xbabio.CarRegistry.controller.mapper.CarMapper;
import com.xbabio.CarRegistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @PostMapping("/car")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDTO){
        try{
            CarDto carSaved = carService.saveCar(carDTO);
            return new ResponseEntity<>(carMapper.toResponse(carSaved), HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/car/{id}")
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        try {
            CarDto carFound = carService.getCar(id);
            return ResponseEntity.ok(carMapper.toResponse(carFound));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/cars")
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    public CompletableFuture<?> getAllCars(){
        return carService.getAllCars().thenApply(carMapper::toResponseList).thenApply(ResponseEntity::ok);
    }

    @PostMapping("/cars/csv")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity saveListCarCSV(@RequestParam("csv") MultipartFile csv){
        if (csv.isEmpty()) {
            log.error("El archivo csv está vacío");
            return ResponseEntity.badRequest().build();
        }
        if (!csv.getOriginalFilename().endsWith(".csv")) {
            log.error("El archivo no es un csv");
            return ResponseEntity.badRequest().build();
        }
        carService.saveListCarCSV(csv);
        return ResponseEntity.ok("El csv se ha cargado con éxito.");
    }

    @PostMapping("/cars")
    @PreAuthorize("hasRole('VENDOR')")
    public CompletableFuture<?> saveListCar(@RequestBody List<CarDto> carDtoList){
        return carService.saveListCar(carDtoList).thenApply(carMapper::toResponseList).thenApply(ResponseEntity::ok);
    }

    @PutMapping("/car/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody CarDto carDTO){
        try {
            CarDto carUpdated = carService.updateById(id,carDTO);
            return ResponseEntity.ok(carMapper.toResponse(carUpdated));
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/car/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        try {
            carService.getCar(id);
            carService.deleteCar(id);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        //log.info("El coche con id {} ha sido borrado.", id);
    }
}
