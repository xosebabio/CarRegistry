package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.CarSaveRequest;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.repository.CarRepository;
import com.xbabio.CarRegistry.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Optional<Car> getCar(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }


    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car mapCarSaveRequestToEntity(CarSaveRequest carSaveRequest) {
        Car car = new Car();
        car.setModel(carSaveRequest.getModel());
        car.setMilleage(carSaveRequest.getMilleage());
        car.setPrice(carSaveRequest.getPrice());
        car.setYear(carSaveRequest.getYear());
        car.setDescription(carSaveRequest.getDescription());
        car.setColour(carSaveRequest.getColour());
        car.setFuelType(carSaveRequest.getFuelType());
        car.setNumDoors(carSaveRequest.getNumDoors());
        car.setBrand(carSaveRequest.getBrand());
        return car;
    }
}
