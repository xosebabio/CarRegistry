package com.xbabio.CarRegistry.repository;

import com.xbabio.CarRegistry.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {

}
