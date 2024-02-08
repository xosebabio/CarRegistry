package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.CarDto;
import com.xbabio.CarRegistry.controller.dtos.CarResponseDto;
import com.xbabio.CarRegistry.entity.Car;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper MAPPER = Mappers.getMapper(CarMapper.class);

    CarDto carToCarDto(Car car);

    Car carDtoToCar(CarDto carDTO);

    CarResponseDto carDtoToCarResponse(CarDto car);

    List<CarDto> carListToCarDtoList(List<Car> carList);
}
