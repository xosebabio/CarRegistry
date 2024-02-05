package com.xbabio.CarRegistry.service.converters;

import com.xbabio.CarRegistry.domain.Car;
import com.xbabio.CarRegistry.entity.CarEntity;

public class CarConverter {

    private BrandConverter brandConverter;

    public Car toCar(CarEntity carEntity) {
        Car car = new Car();
        car.setId(carEntity.getId());
        car.setModel(carEntity.getModel());
        car.setYear(carEntity.getYear());
        car.setMilleage(carEntity.getMilleage());
        car.setPrice(carEntity.getPrice());
        car.setDescription(carEntity.getDescription());
        car.setColour(carEntity.getColour());
        car.setFuelType(carEntity.getFuelType());
        car.setNumDoors(carEntity.getNumDoors());
        car.setBrand(brandConverter.toBrand(carEntity.getBrandEntity()));
        return car;
    }

    public CarEntity toEntity(Car car) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setModel(car.getModel());
        carEntity.setYear(car.getYear());
        carEntity.setMilleage(car.getMilleage());
        carEntity.setPrice(car.getPrice());
        carEntity.setDescription(car.getDescription());
        carEntity.setColour(car.getColour());
        carEntity.setFuelType(car.getFuelType());
        carEntity.setNumDoors(car.getNumDoors());
        carEntity.setBrandEntity(brandConverter.toEntity(car.getBrand()));
        return carEntity;
    }
}
