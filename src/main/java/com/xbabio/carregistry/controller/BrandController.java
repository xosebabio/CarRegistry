package com.xbabio.carregistry.controller;


import com.xbabio.carregistry.controller.dtos.BrandDto;
import com.xbabio.carregistry.controller.dtos.BrandResponseDto;
import com.xbabio.carregistry.controller.mapper.BrandMapper;
import com.xbabio.carregistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandMapper brandMapper;

    @PostMapping("/brand")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> addBrand(@RequestBody BrandDto brandDTO) {
        try {
            BrandResponseDto response = brandMapper.toResponse(brandService.saveBrand(brandDTO));
            //log.info("Marca creada: " + brand.toString());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear la marca", e);
            return new ResponseEntity<>("Error al crear la marca", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/brand/{id}")
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    public ResponseEntity<?> getBrand(@PathVariable Integer id){
        try {
            BrandDto brandFound = brandService.getBrand(id);
            return ResponseEntity.ok(brandMapper.toResponse(brandFound));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        //log.info("La marca es: " + brandFound.toString());
    }

    @GetMapping("/brands")
    @PreAuthorize("hasAnyRole('VENDOR','CLIENT')")
    public CompletableFuture<?> getAllBrands(){
        return brandService.getAllBrands().thenApply(ResponseEntity::ok);
    }

    @PostMapping("/brands")
    @PreAuthorize("hasRole('VENDOR')")
    public CompletableFuture<?> saveAllBrands(@RequestBody List<BrandDto> brandDtoList){
        return brandService.saveListBrand(brandDtoList).thenAccept(brandMapper::toResponseList).thenApply(ResponseEntity::ok);
    }

    @PutMapping("/brand/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> updateBrand(@PathVariable Integer id, @RequestBody BrandDto brandDto){
        try {
            BrandDto brandUpdated = brandService.updateById(id,brandDto);
            return ResponseEntity.ok(brandMapper.toResponse(brandUpdated));
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/brand/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id){
        try {
            BrandDto brand = brandService.getBrand(id);
            brandService.deleteBrand(brand);
            //log.info("La marca con id {} ha sido borrada.", id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }


}
