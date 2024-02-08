package com.xbabio.CarRegistry.controller;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import com.xbabio.CarRegistry.controller.mapper.CarMapper;
import com.xbabio.CarRegistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarMapper carMapper;

    @PostMapping("/car")
    public ResponseEntity<?> addCar(@RequestBody CarDto carDTO){
        try{
            CarDto carSaved = carService.saveCar(carDTO);
            return new ResponseEntity<>(carMapper.carDtoToCarResponse(carSaved), HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<?> getCar(@PathVariable Integer id){
        try {
            CarDto carFound = carService.getCar(id);
            return ResponseEntity.ok(carMapper.carDtoToCarResponse(carFound));
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
    public ResponseEntity<?> updateCar(@PathVariable Integer id, @RequestBody CarDto carDTO){
        try {
            CarDto carUpdated = carService.updateById(id,carDTO);
            return ResponseEntity.ok(carMapper.carDtoToCarResponse(carUpdated));
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id){
        try {
            CarDto car = carService.getCar(id);
            carService.deleteCar(id);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
        //log.info("El coche con id {} ha sido borrado.", id);
    }
}
