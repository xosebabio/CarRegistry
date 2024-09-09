package com.xbabio.CarRegistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponseDto {
    private Integer id;
    private String name;
    private Integer warranty;
    private String country;
}
