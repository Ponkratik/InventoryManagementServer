package com.ponkratov.inventorymanagementserver.repository;

import com.ponkratov.inventorymanagementserver.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    Optional<Responsible> findByUsername(String username);
    Optional<Responsible> findByResponsibleId(Long responsibleId);
    Boolean existsByUsername(String username);
}
