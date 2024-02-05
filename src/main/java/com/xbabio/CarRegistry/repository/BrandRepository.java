package com.xbabio.CarRegistry.repository;

import com.xbabio.CarRegistry.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
}
