package com.xbabio.CarRegistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "warranty")
    private Integer warranty;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "brand")
    private List<CarEntity> carEntities;
}
