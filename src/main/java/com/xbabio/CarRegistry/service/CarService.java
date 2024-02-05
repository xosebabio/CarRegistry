package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.CarRequest;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.entity.CarEntity;

import java.util.List;

public interface CarService {
    Car getCar(Integer id);
    Car saveCar(Car car);
    void deleteCar(Integer id);
    List<Car> getAllCars();
    Car updateById(Integer id, Car car);
}
