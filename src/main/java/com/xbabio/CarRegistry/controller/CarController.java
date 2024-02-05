package com.xbabio.CarRegistry.controller;

import com.xbabio.CarRegistry.controller.dtos.CarRequest;
import com.xbabio.CarRegistry.controller.mapper.CarMapper;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.entity.CarEntity;
import com.xbabio.CarRegistry.service.CarService;
import jakarta.annotation.Resource;
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

    @Resource
    private CarMapper carMapper;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody CarRequest carRequest){
        try{
            Car car = carMapper.carRequestToCar(carRequest);
            Car carSaved = carService.saveCar(car);
            return new ResponseEntity<>(carMapper.carToCarResponse(carSaved), HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        try {
            Car carFound = carService.getCar(id);
            return ResponseEntity.ok(carMapper.carToCarResponse(carFound));
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody CarRequest carRequest){
        try {
            Car carUpdated = carService.updateById(id,carMapper.carRequestToCar(carRequest));
            return ResponseEntity.ok(carMapper.carToCarResponse(carUpdated));
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        try {
            Car car = carService.getCar(id);
            carService.deleteCar(id);
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        //log.info("El coche con id {} ha sido borrado.", id);
        return ResponseEntity.ok().build();
    }
}
