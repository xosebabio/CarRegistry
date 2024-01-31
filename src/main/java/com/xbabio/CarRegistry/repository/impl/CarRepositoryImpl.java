package com.xbabio.CarRegistry.repository.impl;

import com.xbabio.CarRegistry.Utils;
import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.repository.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    ArrayList<Car> cars = new ArrayList<Car>();

    @Override
    public Optional<Car> getCar(Integer id) {
        return Utils.carById(cars,id);
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public Car updateCar(Car car) {
        Utils.updateCarById(cars, car);
        return car;
    }

    @Override
    public void deleteCar(Integer id) {
        Utils.deleteCar(id, cars);
    }


}
