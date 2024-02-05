package com.xbabio.CarRegistry.controller.dtos;

import com.xbabio.CarRegistry.entity.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private Integer id;
    private String name;
    private Integer warranty;
    private String country;
    private List<CarEntity> carEntities;
}
