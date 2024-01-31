package com.xbabio.CarRegistry.controller;

import com.xbabio.CarRegistry.controller.dtos.CarSaveRequest;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody CarSaveRequest carRequest){
        Car car = carService.mapCarSaveRequestToEntity(carRequest);
        carService.saveCar(car);
        log.info("Coche creado: " + car.toString());
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        Optional<Car> carFound = carService.getCar(id);
        if (carFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        log.info("El coche es: " + carFound.toString());
        return ResponseEntity.ok(carFound);
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PutMapping("/car")
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        try {
            carService.saveCar(car);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        Optional<Car> car = carService.getCar(id);
        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        carService.deleteCar(car.get());
        log.info("El coche con id {} ha sido borrado.", id);
        return ResponseEntity.ok().build();
    }
}
