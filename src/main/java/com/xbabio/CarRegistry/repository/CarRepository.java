package com.xbabio.CarRegistry.repository;

import com.xbabio.CarRegistry.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
