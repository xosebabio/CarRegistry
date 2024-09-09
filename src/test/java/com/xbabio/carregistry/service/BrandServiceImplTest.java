package com.xbabio.carregistry.service;

import com.xbabio.carregistry.controller.dtos.BrandDto;
import com.xbabio.carregistry.controller.mapper.BrandMapper;
import com.xbabio.carregistry.entity.Brand;
import com.xbabio.carregistry.repository.BrandRepository;
import com.xbabio.carregistry.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void testGetBrand() throws Exception {
        Integer id = 1;
        BrandDto expectedBrandDto = new BrandDto(1, "Toyota", 2, "Japan");
        Brand expectedBrand = new Brand(1, "Toyota", 2, "Japan");
        when(brandRepository.findById(id)).thenReturn(Optional.of(expectedBrand));
        when(brandMapper.toDto(expectedBrand)).thenReturn(expectedBrandDto);
        BrandDto result = brandService.getBrand(id);
        assertEquals(expectedBrandDto, result);
    }

    @Test
    void testGetBrandNotFound() {
        Integer id = 1;
        when(brandRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> brandService.getBrand(id));
    }

    @Test
    void testSaveBrand() {
        BrandDto brandDto = new BrandDto(null, "Toyota", 2, "Japan");
        Brand expectedBrand = new Brand(null, "Toyota", 2, "Japan");
        BrandDto expectedBrandDto = new BrandDto(1, "Toyota", 2, "Japan");
        when(brandMapper.toBrand(brandDto)).thenReturn(expectedBrand);
        when(brandRepository.save(expectedBrand)).thenReturn(expectedBrand);
        when(brandMapper.toDto(expectedBrand)).thenReturn(expectedBrandDto);
        BrandDto result = brandService.saveBrand(brandDto);
        assertEquals(expectedBrandDto, result);
        verify(brandRepository, times(1)).save(expectedBrand);
    }

    @Test
    void testDeleteBrand() {
        BrandDto brandDto = new BrandDto(1, "Toyota", 2, "Japan");
        Brand expectedBrand = new Brand(1, "Toyota", 2, "Japan");
        when(brandMapper.toBrand(brandDto)).thenReturn(expectedBrand);
        brandService.deleteBrand(brandDto);
        verify(brandRepository, times(1)).delete(expectedBrand);
    }

    @Test
    void testGetAllBrands() throws ExecutionException, InterruptedException {
        List<BrandDto> expectedBrandList = Arrays.asList(
                new BrandDto(1, "Toyota", 2, "Japan"),
                new BrandDto(2, "Honda", 3, "Japan")
        );
        List<Brand> mockBrandList = Arrays.asList(
                new Brand(1, "Toyota", 2, "Japan"),
                new Brand(2, "Honda", 3, "Japan")
        );
        when(brandRepository.findAll()).thenReturn(mockBrandList);
        when(brandMapper.toDtoList(mockBrandList)).thenReturn(expectedBrandList);
        CompletableFuture<List<BrandDto>> resultFuture = brandService.getAllBrands();
        List<BrandDto> result = resultFuture.get();
        assertEquals(expectedBrandList, result);
    }

    @Test
    void testSaveListBrand() throws ExecutionException, InterruptedException {
        List<BrandDto> inputBrandDtoList = Arrays.asList(
                new BrandDto(1, "Toyota", 2, "Japan"),
                new BrandDto(2, "Honda", 3, "Japan")
        );
        List<Brand> expectedSavedBrandList = Arrays.asList(
                new Brand(1, "Toyota", 2, "Japan"),
                new Brand(2, "Honda", 3, "Japan")
        );
        when(brandMapper.toList(inputBrandDtoList)).thenReturn(expectedSavedBrandList);
        when(brandRepository.saveAll(expectedSavedBrandList)).thenReturn(expectedSavedBrandList);
        when(brandMapper.toDtoList(expectedSavedBrandList)).thenReturn(inputBrandDtoList);
        CompletableFuture<List<BrandDto>> resultFuture = brandService.saveListBrand(inputBrandDtoList);
        List<BrandDto> result = resultFuture.get();
        assertEquals(inputBrandDtoList, result);
    }

    @Test
    void testUpdateById() throws Exception {
        Integer id = 1;
        BrandDto brandDto = new BrandDto(1, "Toyota", 2, "Japan");
        Brand existingBrand = new Brand(1, "OldBrand", 1, "OldCountry");
        Brand updatedBrand = new Brand(1, "Toyota", 2, "Japan");
        when(brandRepository.findById(id)).thenReturn(Optional.of(existingBrand));
        when(brandMapper.toBrand(brandDto)).thenReturn(updatedBrand);
        when(brandRepository.save(updatedBrand)).thenReturn(updatedBrand);
        when(brandMapper.toDto(updatedBrand)).thenReturn(brandDto);
        BrandDto result = brandService.updateById(id, brandDto);
        assertEquals(brandDto, result);
        verify(brandRepository, times(1)).save(updatedBrand);
    }

    @Test
    void testUpdateByIdNotFound() {
        Integer id = 1;
        BrandDto brandDto = new BrandDto(1, "Toyota", 2, "Japan");
        when(brandRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> brandService.updateById(id, brandDto));
    }
}
