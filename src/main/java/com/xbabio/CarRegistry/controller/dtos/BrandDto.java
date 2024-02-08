package com.xbabio.CarRegistry.controller.dtos;

import com.xbabio.CarRegistry.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    private Integer id;
    private String name;
    private Integer warranty;
    private String country;
}
