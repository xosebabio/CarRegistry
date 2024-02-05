package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.BrandRequest;
import com.xbabio.CarRegistry.entity.BrandEntity;
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
    public Optional<BrandEntity> getBrand(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public void saveBrand(BrandEntity brandEntity) {
        brandRepository.save(brandEntity);
    }

    @Override
    public void deleteBrand(BrandEntity brandEntity) {
        brandRepository.delete(brandEntity);
    }

    @Override
    public List<BrandEntity> getAllBrands() {
        return brandRepository.findAll();
    }

}
