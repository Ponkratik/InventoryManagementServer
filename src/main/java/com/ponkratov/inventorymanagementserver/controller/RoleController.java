package com.ponkratov.inventorymanagementserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface RoleController {
    @GetMapping("/get/all")
    //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getAllRoles();

    @GetMapping("/get/{id}")
    //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getRoleById(@PathVariable("id") Long roleId);

    @GetMapping("/get/{id}/name")
        //@PreAuthorize("hasRole('ROLE_SYSADMIN')")
    ResponseEntity<?> getRoleByRoleName(@PathVariable("id") Long roleId);
}
