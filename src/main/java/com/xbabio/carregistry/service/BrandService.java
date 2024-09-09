package com.xbabio.carregistry.service;

import com.xbabio.carregistry.controller.dtos.BrandDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface BrandService {
    BrandDto getBrand(Integer id) throws Exception;
    BrandDto saveBrand(BrandDto brandDto);
    void deleteBrand(BrandDto brandDto);
    CompletableFuture<List<BrandDto>> getAllBrands();
    CompletableFuture<List<BrandDto>> saveListBrand(List<BrandDto> brandDtoList);
    BrandDto updateById(Integer id, BrandDto brandDto) throws Exception;
}
