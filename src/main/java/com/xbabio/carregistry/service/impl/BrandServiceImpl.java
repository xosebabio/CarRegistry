package com.xbabio.carregistry.service.impl;

import com.xbabio.carregistry.controller.dtos.BrandDto;
import com.xbabio.carregistry.controller.mapper.BrandMapper;
import com.xbabio.carregistry.entity.Brand;
import com.xbabio.carregistry.repository.BrandRepository;
import com.xbabio.carregistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
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
        return brandMapper.toDto(brandFound.get());
    }

    @Override
    public BrandDto saveBrand(BrandDto brandDto) {
        Brand brandSaved = brandRepository.save(brandMapper.toBrand(brandDto));
        return brandMapper.toDto(brandSaved);
    }

    @Override
    public void deleteBrand(BrandDto brandDto) {
        brandRepository.delete(brandMapper.toBrand(brandDto));
    }

    @Override
    @Async
    public CompletableFuture<List<BrandDto>> getAllBrands() {
        long startTime = System.currentTimeMillis();
        log.info("Obteniendo todas las marcas...");
        List<BrandDto> brandList = brandMapper.toDtoList(brandRepository.findAll());
        long finishTime = System.currentTimeMillis();
        log.info("Tiempo para obtener todas las marcas: " + (finishTime - startTime) + "ms");
        return CompletableFuture.completedFuture(brandList);
    }

    @Override
    @Async
    public CompletableFuture<List<BrandDto>> saveListBrand(List<BrandDto> brandDtoList) {
        long startTime = System.currentTimeMillis();
        log.info("Guardando y obteniendo todas las marcas...");
        brandDtoList = brandMapper.toDtoList(brandRepository.saveAll(brandMapper.toList(brandDtoList)));
        long finishTime = System.currentTimeMillis();
        log.info("Tiempo para guardar y obtener todas las marcas: " + (finishTime - startTime) + "ms");
        return CompletableFuture.completedFuture(brandDtoList);
    }

    @Override
    public BrandDto updateById(Integer id, BrandDto brandDto) throws Exception {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()){
            brandDto.setId(id);
            Brand entity = brandMapper.toBrand(brandDto);
            return brandMapper.toDto(brandRepository.save(entity));
        }else
            throw new Exception();
    }

}
