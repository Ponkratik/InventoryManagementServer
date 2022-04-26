package com.ponkratov.inventorymanagementserver.repository;

import com.ponkratov.inventorymanagementserver.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Boolean existsByInventoryNumber(String inventoryNumber);
    Boolean existsBySerialNumber(String serialNumber);
}
