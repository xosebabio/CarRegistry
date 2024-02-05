package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.CarRequest;
import com.xbabio.CarRegistry.controller.dtos.CarResponse;
import com.xbabio.CarRegistry.domain.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CarMapper {

    CarMapper MAPPER = Mappers.getMapper(CarMapper.class);

    CarRequest carToCarRequest(Car car);

    Car carRequestToCar(CarRequest carRequest);

    CarResponse carToCarResponse(Car car);

    Car carResponseToCar(CarResponse carResponse);
}
