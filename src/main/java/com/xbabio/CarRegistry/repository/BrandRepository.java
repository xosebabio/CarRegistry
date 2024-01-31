package com.xbabio.CarRegistry.repository;

import com.xbabio.CarRegistry.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
