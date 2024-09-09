package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CarService {
    CarDto getCar(Integer id) throws Exception;
    CarDto saveCar(CarDto carDto);
    void deleteCar(Integer id);
    CompletableFuture<List<CarDto>> getAllCars();
    CompletableFuture<List<CarDto>> saveListCar(List<CarDto> carDtoList);
    CarDto updateById(Integer id, CarDto carDto) throws Exception;
    void saveListCarCSV(MultipartFile csv);
}
