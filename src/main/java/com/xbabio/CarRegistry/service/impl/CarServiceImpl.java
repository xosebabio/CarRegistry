package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.repository.CarRepository;
import com.xbabio.CarRegistry.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Optional<Car> getCar(Integer id) {
        return carRepository.getCar(id);
    }

    @Override
    public void addCar(Car car) {
        carRepository.addCar(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.updateCar(car);
    }

    @Override
    public void deleteCar(Integer id) {
        carRepository.deleteCar(id);
    }
}
