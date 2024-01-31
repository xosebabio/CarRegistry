package com.xbabio.CarRegistry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "milleage")
    private Integer milleage;

    @Column(name = "price")
    private Double price;

    @Column(name = "year")
    private Integer year;

    @Column(name = "description")
    private String description;

    @Column(name = "colour")
    private String colour;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "num_doors")
    private Integer numDoors;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore // Evita la serialización de la marca al serializar Car
    private Brand brand;
}
