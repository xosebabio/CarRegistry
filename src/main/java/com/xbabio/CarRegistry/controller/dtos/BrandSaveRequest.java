package com.xbabio.CarRegistry.controller.dtos;

import com.xbabio.CarRegistry.domain.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandSaveRequest {
    private String name;
    private Integer warranty;
    private String country;
    private List<Car> cars;
}
