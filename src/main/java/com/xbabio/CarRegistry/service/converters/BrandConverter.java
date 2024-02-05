package com.xbabio.CarRegistry.service.converters;

import com.xbabio.CarRegistry.domain.Brand;
import com.xbabio.CarRegistry.entity.BrandEntity;

public class BrandConverter {

    public Brand toBrand(BrandEntity brandEntity) {
        return new Brand(brandEntity.getId(), brandEntity.getName(), brandEntity.getWarranty(), brandEntity.getCountry());
    }

    public BrandEntity toEntity(Brand brand) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brand.getId());
        brandEntity.setName(brand.getName());
        brandEntity.setWarranty(brand.getWarranty());
        brandEntity.setCountry(brand.getCountry());
        return brandEntity;
    }
}
