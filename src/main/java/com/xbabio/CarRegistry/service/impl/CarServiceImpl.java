package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import com.xbabio.CarRegistry.controller.mapper.CarMapper;
import com.xbabio.CarRegistry.entity.Car;
import com.xbabio.CarRegistry.repository.CarRepository;
import com.xbabio.CarRegistry.service.CarService;
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

    @Autowired
    private CarMapper carMapper;

    @Override
    public CarDto getCar(Integer id) throws Exception {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new Exception();
        return carMapper.carToCarDto(carOptional.get());
    }

    @Override
    public CarDto saveCar(CarDto car) {
        log.info("Adding car to the Database...");
        Car newCar = carRepository.save(carMapper.carDtoToCar(car));
        return carMapper.carToCarDto(newCar);
    }


    @Override
    public void deleteCar(Integer id) {
        log.info("Deleting car with id: " + id);
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> getAllCars() {
        return carMapper.carListToCarDtoList(carRepository.findAll());
    }

    @Override
    public CarDto updateById(Integer id, CarDto car) throws Exception {
        log.info("Updating car with id: " + id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new Exception();
        car.setId(id);
        return carMapper.carToCarDto(carRepository.save(carMapper.carDtoToCar(car)));
    }
}
