package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import com.xbabio.CarRegistry.controller.dtos.CarResponseDto;
import com.xbabio.CarRegistry.entity.Car;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(Car car);

    Car toCar(CarDto carDTO);

    CarResponseDto toResponse(CarDto car);

    List<CarDto> toDtoList(List<Car> carList);

    List<CarResponseDto> toResponseList(List<CarDto> carList);

    List<Car> toList(List<CarDto> carList);
}
