package com.xbabio.CarRegistry.service;

import com.xbabio.CarRegistry.controller.dtos.BrandSaveRequest;
import com.xbabio.CarRegistry.domain.Brand;

import java.util.List;
import java.util.Optional;


public interface BrandService {
    Optional<Brand> getBrand(Integer id);
    void saveBrand(Brand brand);
    void deleteBrand(Brand brand);
    List<Brand> getAllBrands();
    Brand mapBrandSaveRequestToEntity(BrandSaveRequest brandSaveRequest);
}
