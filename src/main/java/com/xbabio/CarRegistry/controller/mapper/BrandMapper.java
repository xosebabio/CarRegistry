package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.BrandDto;
import com.xbabio.CarRegistry.controller.dtos.BrandResponseDto;
import com.xbabio.CarRegistry.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto brandToBrandDto(Brand brand);

    BrandResponseDto brandDtoToBrandResponse(BrandDto brand);

    Brand brandDtoToBrand(BrandDto brandDTO);

    List<BrandDto> brandListToBrandDtoList(List<Brand> brandList);
}
