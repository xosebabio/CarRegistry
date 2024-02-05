package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.BrandRequest;
import com.xbabio.CarRegistry.controller.dtos.BrandResponse;
import com.xbabio.CarRegistry.domain.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BrandMapper {

    BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandRequest brandToBrandRequest(Brand brand);

    BrandResponse brandToBrandResponse(Brand brand);

    Brand brandRequestToBrand(BrandRequest brandRequest);

    Brand brandResponseToBrand(BrandResponse brandResponse);
}
