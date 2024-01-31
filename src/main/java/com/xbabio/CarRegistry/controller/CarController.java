package com.xbabio.CarRegistry.controller;

import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody Car car){
        carService.addCar(car);
        System.out.println("Coche creado: " + car.toString());
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        Optional<Car> carFound = carService.getCar(id);
        if (carFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        System.out.println("El coche es: " + carFound.toString());
        return ResponseEntity.ok(carFound);
    }

    @PutMapping("/car")
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        Car carUpdated = carService.updateCar(car);
        if (carUpdated == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("El coche actualizado queda: " + carUpdated.toString());
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        try {
            carService.deleteCar(id);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        System.out.println("El coche ha sido borrado");
        return ResponseEntity.ok().build();
    }
}
