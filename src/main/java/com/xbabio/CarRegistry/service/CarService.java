package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.CarDto;

import java.util.List;

public interface CarService {
    CarDto getCar(Integer id) throws Exception;
    CarDto saveCar(CarDto carDto);
    void deleteCar(Integer id);
    List<CarDto> getAllCars();
    CarDto updateById(Integer id, CarDto carDto) throws Exception;
}
