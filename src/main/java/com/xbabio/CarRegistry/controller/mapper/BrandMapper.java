package com.xbabio.CarRegistry.controller.mapper;

import com.xbabio.CarRegistry.controller.dtos.BrandDto;
import com.xbabio.CarRegistry.controller.dtos.BrandResponseDto;
import com.xbabio.CarRegistry.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand brand);

    BrandResponseDto toResponse(BrandDto brand);

    Brand toBrand(BrandDto brandDTO);

    List<BrandDto> toDtoList(List<Brand> brandList);

    List<Brand> toList(List<BrandDto> brandList);

    List<BrandResponseDto> toResponseList(List<BrandDto> brandList);
}
