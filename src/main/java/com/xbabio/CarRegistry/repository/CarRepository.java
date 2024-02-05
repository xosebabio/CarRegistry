package com.xbabio.CarRegistry.repository;

import com.xbabio.CarRegistry.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {

}
