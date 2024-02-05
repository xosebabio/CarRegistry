package com.xbabio.CarRegistry.controller.dtos;

import com.xbabio.CarRegistry.entity.BrandEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private Integer id;
    private String model;
    private Integer milleage;
    private Double price;
    private Integer year;
    private String description;
    private String colour;
    private String fuelType;
    private Integer numDoors;
    private BrandEntity brandEntity;
}
