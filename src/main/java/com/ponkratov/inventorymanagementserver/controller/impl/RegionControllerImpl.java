package com.ponkratov.inventorymanagementserver.controller.impl;

import com.ponkratov.inventorymanagementserver.controller.RegionController;
import com.ponkratov.inventorymanagementserver.model.Region;
import com.ponkratov.inventorymanagementserver.payload.response.MessageResponse;
import com.ponkratov.inventorymanagementserver.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionControllerImpl implements RegionController {
    @Autowired
    RegionRepository regionRepository;

    @Override
    public ResponseEntity<?> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getRegionById(Long regionId) {
        Optional<Region> temp = regionRepository.findById(regionId);
        Region tempRegion;
        if (temp.isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("Не удалось найти такой регион"), HttpStatus.NO_CONTENT);
        } else {
            tempRegion = temp.get();
        }

        return new ResponseEntity<>(tempRegion, HttpStatus.OK);
    }
}
