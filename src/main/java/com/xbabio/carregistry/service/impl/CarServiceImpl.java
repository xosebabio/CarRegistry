package com.xbabio.carregistry.service.impl;

import com.xbabio.carregistry.controller.dtos.CarDto;
import com.xbabio.carregistry.controller.mapper.CarMapper;
import com.xbabio.carregistry.entity.Car;
import com.xbabio.carregistry.repository.CarRepository;
import com.xbabio.carregistry.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
        return carMapper.toDto(carOptional.get());
    }

    @Override
    public CarDto saveCar(CarDto car) {
        log.info("Guardando coche...");
        Car newCar = carRepository.save(carMapper.toCar(car));
        return carMapper.toDto(newCar);
    }


    @Override
    public void deleteCar(Integer id) {
        log.info("Borrando coche con id: " + id);
        carRepository.deleteById(id);
    }

    @Override
    @Async
    public CompletableFuture<List<CarDto>> getAllCars() {
        long startTime = System.currentTimeMillis();
        log.info("Obteniendo todos los coches...");
        List<CarDto> carDtoList = carMapper.toDtoList(carRepository.findAll());
        long endTime = System.currentTimeMillis();
        log.info("Tiempo para obtener todos los coches: " + (endTime - startTime) + "ms");
        return CompletableFuture.completedFuture(carDtoList);
    }

    @Override
    @Async
    public CompletableFuture<List<CarDto>> saveListCar(List<CarDto> carDtoList) {
        long startTime = System.currentTimeMillis();
        log.info("Guardando todos los coches...");
        carDtoList = carMapper.toDtoList(carRepository.saveAll(carMapper.toList(carDtoList)));
        long endTime = System.currentTimeMillis();
        log.info("Tiempo para guardar y devolver todos los coches: " + (endTime - startTime) + "ms");
        return CompletableFuture.completedFuture(carDtoList);
    }

    @Override
    public CarDto updateById(Integer id, CarDto car) throws Exception {
        log.info("Actualizando coche con id: " + id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isEmpty())
            throw new Exception();
        car.setId(id);
        return carMapper.toDto(carRepository.save(carMapper.toCar(car)));
    }

    @Override
    public void saveListCarCSV(MultipartFile csv) {
        log.info("Guardando coches desde CSV...");
        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getInputStream(), "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Car> carList = new ArrayList<>();
            csvParser.getRecords().forEach(record -> {
                Car car = new Car();
                //car.setBrand(record.get("brand"));
                car.setModel(record.get("model"));
                car.setYear(Integer.parseInt(record.get("year")));
                car.setPrice(Double.parseDouble(record.get("price")));
                car.setMilleage(Integer.parseInt(record.get("milleage")));
                car.setDescription(record.get("description"));
                car.setColour(record.get("colour"));
                car.setFuelType(record.get("fuelType"));
                car.setNumDoors(Integer.parseInt(record.get("numDoors")));
                carList.add(car);
            });
            carRepository.saveAll(carList);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Fallo al guardar coches desde CSV");
        }
    }
}
