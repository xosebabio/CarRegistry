package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.CarSaveRequest;
import com.xbabio.CarRegistry.domain.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getCar(Integer id);
    void saveCar(Car car);
    void deleteCar(Car car);
    List<Car> getAllCars();
    Car mapCarSaveRequestToEntity(CarSaveRequest carSaveRequest);
}
