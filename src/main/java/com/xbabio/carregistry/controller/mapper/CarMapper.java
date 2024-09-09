package com.xbabio.carregistry.controller.mapper;

import com.xbabio.carregistry.controller.dtos.CarDto;
import com.xbabio.carregistry.controller.dtos.CarResponseDto;
import com.xbabio.carregistry.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(Car car);

    Car toCar(CarDto carDTO);

    CarResponseDto toResponse(CarDto car);

    List<CarDto> toDtoList(List<Car> carList);

    List<CarResponseDto> toResponseList(List<CarDto> carList);

    List<Car> toList(List<CarDto> carList);
}
