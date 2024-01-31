package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.domain.Car;

import java.util.Optional;

public interface CarService {
    Optional<Car> getCar(Integer id);
    void addCar(Car car);
    Car updateCar(Car car);
    void deleteCar(Integer id);
}
