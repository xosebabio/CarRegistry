package com.xbabio.carregistry.repository;

import com.xbabio.carregistry.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
