package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.CarRequest;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.entity.CarEntity;
import com.xbabio.CarRegistry.repository.CarRepository;
import com.xbabio.CarRegistry.service.CarService;
import com.xbabio.CarRegistry.service.converters.CarConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    private CarConverter carConverter;

    @Override
    public Car getCar(Integer id) {
        Optional<CarEntity> carOptional = carRepository.findById(id);
        return carOptional.map(carEntity -> carConverter.toCar(carEntity)).orElse(null); // Si carOptional tiene valor, mapea el objeto a Car, sino retorna null
    }

    @Override
    public Car saveCar(Car car) {
        log.info("Adding car to the Database...");
        CarEntity entity = carConverter.toEntity(car);
        return carConverter.toCar(carRepository.save(entity));
    }


    @Override
    public void deleteCar(Integer id) {
        log.info("Deleting car with id: " + id);
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(carEntity -> carConverter.toCar(carEntity)).toList(); // Mapea todos los objetos a Car
    }

    @Override
    public Car updateById(Integer id, Car car) {
        log.info("Updating car with id: " + id);
        Optional<CarEntity> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()){
            CarEntity entity = carConverter.toEntity(car);
            entity.setId(id);
            return carConverter.toCar(carRepository.save(entity));
        }
        return null;
    }
}
