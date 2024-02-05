package com.xbabio.CarRegistry.controller.dtos;

import com.xbabio.CarRegistry.entity.BrandEntity;

public class CarResponse {
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
