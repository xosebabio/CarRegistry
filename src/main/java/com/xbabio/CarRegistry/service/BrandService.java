package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.BrandDto;

import java.util.List;


public interface BrandService {
    BrandDto getBrand(Integer id) throws Exception;
    BrandDto saveBrand(BrandDto brandDto);
    void deleteBrand(BrandDto brandDto);
    List<BrandDto> getAllBrands();
    BrandDto updateById(Integer id, BrandDto brandDto) throws Exception;
}
