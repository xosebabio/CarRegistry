package com.xbabio.CarRegistry.controller;


import com.xbabio.CarRegistry.controller.dtos.BrandDto;
import com.xbabio.CarRegistry.controller.dtos.BrandResponseDto;
import com.xbabio.CarRegistry.controller.mapper.BrandMapper;
import com.xbabio.CarRegistry.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandMapper brandMapper;

    @PostMapping("/brand")
    public ResponseEntity<?> addBrand(@RequestBody BrandDto brandDTO) {
        try {
            BrandResponseDto response = brandMapper.brandDtoToBrandResponse(brandService.saveBrand(brandDTO));
            //log.info("Marca creada: " + brand.toString());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al crear la marca", e);
            return new ResponseEntity<>("Error al crear la marca", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/brand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable Integer id){
        try {
            BrandDto brandFound = brandService.getBrand(id);
            return ResponseEntity.ok(brandMapper.brandDtoToBrandResponse(brandFound));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        //log.info("La marca es: " + brandFound.toString());
    }

    @GetMapping("/brand")
    public ResponseEntity<?> getAllBrands(){
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @PutMapping("/brand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer id, @RequestBody BrandDto brandDto){
        try {
            BrandDto brandUpdated = brandService.updateById(id,brandDto);
            return ResponseEntity.ok(brandMapper.brandDtoToBrandResponse(brandUpdated));
        }catch(Exception e){
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/brand/{id}")
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
