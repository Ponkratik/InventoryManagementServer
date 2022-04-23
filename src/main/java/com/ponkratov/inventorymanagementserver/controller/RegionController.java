package com.ponkratov.inventorymanagementserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/region")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface RegionController {
    @GetMapping("/get/all")
        //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getAllRegions();

    @GetMapping("/get/{id}")
        //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getRegionById(@PathVariable("id") Long regionId);
}
