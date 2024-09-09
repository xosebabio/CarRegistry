package com.xbabio.carregistry.service;

import com.xbabio.carregistry.controller.dtos.CarDto;
import com.xbabio.carregistry.entity.Brand;
import com.xbabio.carregistry.entity.Car;
import com.xbabio.carregistry.repository.CarRepository;
import com.xbabio.carregistry.controller.mapper.CarMapper;
import com.xbabio.carregistry.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testGetCar() throws Exception {
        Integer id = 1;
        CarDto expectedCarDto = new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());
        Car expectedCar = new Car(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());
        when(carRepository.findById(id)).thenReturn(Optional.of(expectedCar));
        when(carMapper.toDto(expectedCar)).thenReturn(expectedCarDto);
        CarDto result = carService.getCar(id);
        assertEquals(expectedCarDto, result);
    }

    @Test
    void testGetCarNotFound() {
        Integer id = 1;
        when(carRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> carService.getCar(id));
    }

    @Test
    void testSaveCar() {
        CarDto carDto = new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());
        Car expectedCar = new Car(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());
        when(carMapper.toCar(carDto)).thenReturn(expectedCar);
        when(carRepository.save(expectedCar)).thenReturn(expectedCar);
        when(carMapper.toDto(expectedCar)).thenReturn(carDto);
        CarDto result = carService.saveCar(carDto);
        assertEquals(carDto, result);
    }

    @Test
    void testDeleteCar() {
        Integer id = 1;
        carService.deleteCar(id);
        verify(carRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetAllCars() throws Exception {
        List<CarDto> expectedCarDtoList = Arrays.asList(
                new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand()),
                new CarDto(2, "Honda Accord", 45000, 23000.0, 2021, "Sedan", "Red", "Petrol", 4, new Brand())
        );
        List<Car> mockCarList = Arrays.asList(
                new Car(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand()),
                new Car(2, "Honda Accord", 45000, 23000.0, 2021, "Sedan", "Red", "Petrol", 4, new Brand())
        );
        when(carRepository.findAll()).thenReturn(mockCarList);
        when(carMapper.toDtoList(mockCarList)).thenReturn(expectedCarDtoList);
        CompletableFuture<List<CarDto>> resultFuture = carService.getAllCars();
        List<CarDto> result = resultFuture.get();
        assertEquals(expectedCarDtoList, result);
    }

    @Test
    void testSaveListCar() throws Exception {
        List<CarDto> inputCarDtoList = Arrays.asList(
                new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand()),
                new CarDto(2, "Honda Accord", 45000, 23000.0, 2021, "Sedan", "Red", "Petrol", 4, new Brand())
        );

        List<Car> expectedSavedCarList = Arrays.asList(
                new Car(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand()),
                new Car(2, "Honda Accord", 45000, 23000.0, 2021, "Sedan", "Red", "Petrol", 4, new Brand())
        );

        when(carMapper.toList(inputCarDtoList)).thenReturn(expectedSavedCarList);
        when(carRepository.saveAll(expectedSavedCarList)).thenReturn(expectedSavedCarList);
        when(carMapper.toDtoList(expectedSavedCarList)).thenReturn(inputCarDtoList);

        CompletableFuture<List<CarDto>> resultFuture = carService.saveListCar(inputCarDtoList);
        List<CarDto> result = resultFuture.get();

        assertEquals(inputCarDtoList, result);
    }

    @Test
    void testUpdateById() throws Exception {
        Integer id = 1;
        CarDto carDto = new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());

        Car existingCar = new Car(1, "OldCar", 1, 1.0, 2000, "OldType", "OldColor", "OldFuel", 2, new Brand());
        Car updatedCar = new Car(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());

        when(carRepository.findById(id)).thenReturn(Optional.of(existingCar));
        when(carMapper.toCar(carDto)).thenReturn(updatedCar);
        when(carRepository.save(updatedCar)).thenReturn(updatedCar);
        when(carMapper.toDto(updatedCar)).thenReturn(carDto);

        CarDto result = carService.updateById(id, carDto);

        assertEquals(carDto, result);

        verify(carRepository, times(1)).save(updatedCar);
    }

    @Test
    void testUpdateByIdNotFound() {
        Integer id = 1;
        CarDto carDto = new CarDto(1, "Toyota Camry", 50000, 25000.0, 2020, "Sedan", "Blue", "Petrol", 4, new Brand());

        when(carRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> carService.updateById(id, carDto));
    }
}
