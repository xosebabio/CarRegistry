package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.BrandRequest;
import com.xbabio.CarRegistry.entity.BrandEntity;

import java.util.List;
import java.util.Optional;


public interface BrandService {
    Optional<BrandEntity> getBrand(Integer id);
    void saveBrand(BrandEntity brandEntity);
    void deleteBrand(BrandEntity brandEntity);
    List<BrandEntity> getAllBrands();
}
