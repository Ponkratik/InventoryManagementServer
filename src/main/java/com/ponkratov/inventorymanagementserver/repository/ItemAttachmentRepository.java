package com.ponkratov.inventorymanagementserver.repository;

import com.ponkratov.inventorymanagementserver.model.Item;
import com.ponkratov.inventorymanagementserver.model.Itemattachement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemAttachmentRepository extends JpaRepository<Itemattachement, Long> {
    List<Itemattachement> findByItemByItemId_ItemId(long itemId);
}
