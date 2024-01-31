package com.xbabio.CarRegistry.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "warranty")
    private Integer warranty;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonIgnore // Evita la serialización de la lista de cars al serializar Brand
    private List<Car> cars;
}
