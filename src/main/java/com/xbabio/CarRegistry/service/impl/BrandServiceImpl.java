package com.xbabio.CarRegistry.service.impl;

import com.xbabio.CarRegistry.controller.dtos.BrandDto;
import com.xbabio.CarRegistry.controller.mapper.BrandMapper;
import com.xbabio.CarRegistry.entity.Brand;
import com.xbabio.CarRegistry.repository.BrandRepository;
import com.xbabio.CarRegistry.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public BrandDto getBrand(Integer id) throws Exception {
        Optional<Brand> brandFound = brandRepository.findById(id);
        if (brandFound.isEmpty())
            throw new Exception();
        return brandMapper.brandToBrandDto(brandFound.get());
    }

    @Override
    public BrandDto saveBrand(BrandDto brandDto) {
        Brand brandSaved = brandRepository.save(brandMapper.brandDtoToBrand(brandDto));
        return brandMapper.brandToBrandDto(brandSaved);
    }

    @Override
    public void deleteBrand(BrandDto brandDto) {
        brandRepository.delete(brandMapper.brandDtoToBrand(brandDto));
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandMapper.brandListToBrandDtoList(brandRepository.findAll());
    }

    @Override
    public BrandDto updateById(Integer id, BrandDto brandDto) throws Exception {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()){
            brandDto.setId(id);
            Brand entity = brandMapper.brandDtoToBrand(brandDto);
            return brandMapper.brandToBrandDto(brandRepository.save(entity));
        }else
            throw new Exception();
    }

}
