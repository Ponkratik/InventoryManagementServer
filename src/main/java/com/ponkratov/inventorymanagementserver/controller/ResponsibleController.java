package com.ponkratov.inventorymanagementserver.controller;

import com.ponkratov.inventorymanagementserver.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/responsible")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface ResponsibleController {
    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getAllResponsibles();

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getResponsibleById(@PathVariable("id") Long responsibleId);

    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> deleteResponsible(@PathVariable("id") Long responsibleId);

    @PutMapping ("/update/{id}")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> updateResponsible(@PathVariable("id") Long responsibleId, @Valid @RequestBody SignupRequest request);
}
