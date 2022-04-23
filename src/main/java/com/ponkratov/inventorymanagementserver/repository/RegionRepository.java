package com.ponkratov.inventorymanagementserver.repository;

import com.ponkratov.inventorymanagementserver.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionName(String regionName);
}
