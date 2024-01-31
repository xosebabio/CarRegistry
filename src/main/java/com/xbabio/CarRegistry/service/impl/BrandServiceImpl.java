package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.BrandSaveRequest;
import com.xbabio.CarRegistry.domain.Brand;
import com.xbabio.CarRegistry.repository.BrandRepository;
import com.xbabio.CarRegistry.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Optional<Brand> getBrand(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Brand brand) {
        brandRepository.delete(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand mapBrandSaveRequestToEntity(BrandSaveRequest brandSaveRequest) {
        Brand brand = new Brand();
        brand.setName(brandSaveRequest.getName());
        brand.setCars(brandSaveRequest.getCars());
        brand.setCountry(brandSaveRequest.getCountry());
        brand.setWarranty(brandSaveRequest.getWarranty());
        return brand;
    }
}
